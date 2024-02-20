<template>
  <div id="create-deck">
    <h1 class="head">{{ action }} Deck Form</h1>

    <form v-on:submit.prevent="saveData">
      <input class="form" type="text" placeholder="Title" v-model="newDeck.deckTitle" />
      <br /><br />
      <input class="form" type="text" placeholder="Tags" v-model="newDeck.deckTags" />
      <br /><br />
      <div>
        <button type="submit" class="button">Submit</button>
      </div>
    </form>
  </div>

  <!-- <div id="edit-deck">
    <h1>Edit Deck Form</h1>
    
    <form v-on:submit.prevent="updateDeck">
    <input type="text" placeholder="Title" v-model="newDeck.deckTitle"/> <br/><br/>
    <input type="text" placeholder="Tags" v-model="newDeck.deckTags"/> <br/><br/>
    <div class="btns">
      <input type="submit" class="regular-btn" />
    </div>
    </form>
    
  </div> -->
</template>

<script>
import DeckService from "../services/DeckService";

export default {
  props: {
    deck: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      newDeck: {},
      // editDeck: {
      //   deckTitle: this.newDeck.deckTitle,
      //   deckTags: this.newDeck.deckTags,
      // }
      action: ''
    };
  },
  methods: {
    saveData() {
      if (this.deck && this.deck.deckId) {
        DeckService.updateDeck(this.deck.deckId, this.newDeck).then(resp => {
          this.$store.commit('SET_DECK', this.newDeck);
          this.newDeck = {};
        });
      } else {
        DeckService.createDeck(this.newDeck).then((response) => {
          if (response.status === 201) {
            window.alert("Deck Added!");
            this.$router.push({ name: "home" });
          } else {
            DeckService.updateDeck(this.newDeck.deckId).then((response) => {
              if (response.status === 200) {
                this.$router.push({
                  name: "DeckView",
                  params: { deckId: this.editDeck.deckId },
                });
              }
            });
          }
        });
      }
    },
  },
  created() {
    if (this.$route.params.deckId) {
      DeckService.getDeck(this.$route.params.deckId).then(resp => {
        const deck = resp.data
        this.newDeck.deckId = deck.deckId;
        this.newDeck.deckTitle = deck.deckTitle;
        this.newDeck.deckTags = deck.deckTags;

        this.$store.commit('SET_DECK', deck);

        this.action = "Edit"
      });
    } else {
      this.action = "New"
    }
  },
};
</script>

<style>
#create-deck {
  display: block;
  border-radius: 15px;
  text-align: center;
  margin: 50px auto;
 width: 300px;
 color: white;
  background-color:#acaaaa;
  padding: 20px;
  font-family: Arial, Helvetica, sans-serif;
    font-weight: bold;
    
}

.form {
  border-radius: 5px;
  font-family:Arial, Helvetica, sans-serif;
  border: none;
  font-weight: bold;
}

.button {
  border: none;
  color: black;
  background: white;
  border-radius: 5px;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: bold;
}
</style>
