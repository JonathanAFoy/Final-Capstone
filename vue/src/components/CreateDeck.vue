<template>
  <div id="create-deck">
    <h1>New Deck Form</h1>

    <form v-on:submit.prevent="saveData">
      <input type="text" placeholder="Title" v-model="newDeck.deckTitle" />
      <br /><br />
      <input type="text" placeholder="Tags" v-model="newDeck.deckTags" />
      <br /><br />
      <div class="btns">
        <input type="submit" class="regular-btn" />
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
    };
  },
  methods: {
    saveData() {
      if (this.deck && this.deck.deckId) {
        DeckService.updateDeck(this.deck.deckId, this.newDeck).then( resp => {
          this.$store.commit('SET_DECK', this.newDeck);

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
    if (this.deck) {
      this.newDeck.deckId = this.deck.deckId;
      this.newDeck.deckTitle = this.deck.deckTitle;
      this.newDeck.deckTags = this.deck.deckTags;
    }
  },
};
</script>

<style>
#create-deck {
  border-radius: 15px;
  text-align: center;
  margin: 50px auto;
  max-width: 500px;
  background-color: #f7f9ff;
  padding: 30px;
  box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.2);
}

/* .btns {
  background-color: #3498db;
} */
</style>
