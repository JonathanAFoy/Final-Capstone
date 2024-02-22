<template>
  <div id="create-card">
    <h1 id="card-head">{{ action }} Card Form</h1>

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

      <!-- Return Button is here because center alignment for other buttons -->
      <button
        class="button"
        v-if="action === 'Edit'"
        @click.prevent="returnHome"
      >
        Go Back
      </button>
      &nbsp; &nbsp; &nbsp;

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

      <button
        class="button"
        v-if="action === 'Edit'"
        @click.prevent="showDeckList"
      >
        Show Decks To Add
      </button>

      <!-- Submit Button is here because center alignment for other buttons -->
      &nbsp; &nbsp; &nbsp;
      <button type="submit" class="button" id="bottom" v-if="action === 'Edit'">
        Submit
      </button>
    </form>
  </div>

  <div class="edit-button-section">
    <button
      type="submit"
      class="edit-button"
      v-if="addToDeckId"
      @click="addToDeck"
    >
      Add to Deck
    </button>
    <br /><br />
  </div>

  <div class="deck-display">
    <DecksList v-bind:deckList="deckList" v-if="showDecks" />
  </div>

  <div class="edit-button-section">
    <br /><br />
    <button class="edit-button" v-if="showDecks" @click.prevent="showDeckList">
      Cancel
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
      this.$store.commit("SET_EDIT_DECK_ID", null);
    },
    returnHome() {
      this.showDecks = !this.showDecks;
      this.$store.commit("SET_EDIT_DECK_ID", null);
      this.$router.push({ name: "home" });
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
      DeckService.addCard(
        this.$store.state.editModeDeckId,
        this.card.cardId
      ).then((response) => {
        this.showDecks = !this.showDecks;
        this.$store.commit("SET_EDIT_DECK_ID", null);
      });
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
  background-color: rgba(68, 118, 255, 0.785);
  padding: 20px;
  box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.2);
}

#card-head {
  margin-top: 5px;
  margin-bottom: 20px;
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

#bottom {
  margin-bottom: 10px;
}

.edit-button {
  border: none;
  color: black;
  background: white;
  border-radius: 5px;
  font-family: Arial, Helvetica, sans-serif;
  font-weight: bold;
}

.edit-button-section {
  display: flex;
  justify-content: center;
  text-align: center;
  align-items: center;
}
</style>
