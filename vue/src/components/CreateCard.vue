<template>
  <div id="create-card">
    <h1 class="head">{{ action }} Card Form</h1>

    <form v-on:submit.prevent="customizedCardForm">
      <input
        class="form"
        type="text"
        placeholder="Front Text"
        v-model="newCard.frontText"
      />
      <br /><br />
      <input
        class="form"
        type="text"
        placeholder="Back Text"
        v-model="newCard.backText"
      />
      <br /><br />
      <input
        class="form"
        type="text"
        placeholder="Tags"
        v-model="newCard.cardTags"
      />
      <br /><br />

      <button
        type="submit"
        class="button"
        v-if="action === 'New'"
        @click="refreshHome()"
      >
        Submit
      </button>
      <button type="submit" class="button" v-if="action === 'Create'">
        Submit
      </button>
      <button type="submit" class="button" v-if="action === 'Edit'">
        Submit
      </button>
      <button class="button" v-if="action === 'Edit'"  v-on:click.prevent="showDeckList">
        Show Decks To Add
      </button>
      
    </form>
  </div>
  <div class="deck-display">
  
    
      <DecksList v-bind:deckList="deckList" v-if="showDecks" />
    
      
  </div>
  <div>
  <button
        type="submit"
        class="button"
        @click="addToDeck"
        v-if="addToDeckId"
      >
        Add to Deck
      </button>
    </div>
</template>
  
<script>
import CardService from "../services/CardService";
import DeckService from "../services/DeckService";
import DecksList from "../components/DecksList.vue";

export default {
  props: ["deckId"],
  components: {
    DecksList,
  },
  data() {
    return {
      newCard: {},
      card: {},
      action: "",
      deckList: [],
      showDecks: false,
    };
  },
  methods: {
    showDeckList() {
      this.showDecks = !this.showDecks;
    },
    refreshHome() {
      location.reload
        ? location.reload()
        : (location = this.$router.push({ name: "home" }));
    },

    customizedCardForm() {
      if (this.card && this.card.cardId) {
        CardService.updateCard(this.card.cardId, this.newCard).then((resp) => {
          this.$store.commit("SET_CARD", this.newCard);
          this.$router.push({ name: "home" });
        });
      } else {
        CardService.createCard(this.newCard).then((response) => {
          if (response.status === 201) {
            if (!this.deckId) {
              this.$router.push({ name: "home" });
              this.newCard = {};
            } else {
              DeckService.addCard(this.deckId, response.data.cardId).then(
                (resp) => {
                  let cardData = response.data;
                  cardData.username = this.$store.state.user.username;
                  this.$store.commit("ADD_CARD_CURRDECK", response.data);
                }
              );
            }
          }
        });
      }
    },
    addToDeck() {
      DeckService.addCard(this.$store.state.editModeDeckId, this.card.cardId);
      // this.$store.commit("SET_EDIT_DECK_ID", null);
    },
  },
  computed: {
    addToDeckId() {
      return this.$store.state.editModeDeckId;
    },
  },
  created() {
    if (this.$route.params.cardId) {
      DeckService.getDecks().then((response) => {
        this.deckList = response.data;
      });
      CardService.getCard(this.$route.params.cardId).then((resp) => {
        this.card = resp.data;
        this.newCard.cardId = this.card.cardId;
        this.newCard.frontText = this.card.frontText;
        this.newCard.backText = this.card.backText;
        this.newCard.cardTags = this.card.cardTags;
        this.$store.commit("SET_CARD", this.card);
        this.$store.commit("UPDATE_EDIT_STATUS", true);
        this.action = "Edit";
      });
    }
    if (this.$route.params.deckId) {
      this.action = "Create";
    } else {
      this.action = "New";
    }
  },
};
</script>
  
<style>
#create-card {
  display: block;
  border-radius: 30px;
  text-align: center;
  margin: 50px auto;
  max-width: 350px;
  background-color: rgb(68, 118, 255);
  padding: 30px;
  box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.2);
}
.head {
  color: white;
  font-family: Arial, Helvetica, sans-serif;
}

.form {
  border: none;
  border-radius: 5px;
  font-family: Arial, Helvetica, sans-serif;
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
