<template>
  <!-- Display for HomeView, shows all card properties -->

  <!--
    <div class="card" name="home-view" v-if="from === 'home'" v-on:click="flip(cardData)">-->
  <!-- Button to delete card from database -->

  <!--
        <button class="x" v-on:click="deleteCard(card.cardId)">X</button>
        <div class="head">

            <p>{{ cardData.cardId }} {{ cardData.username }}</p>
        </div>
        <div class="body">
            <p>{{ cardData.frontText }}</p>
        </div>
        <div class="foot">
            <p>{{ cardData.tags }} {{ cardData.public }}</p>
        </div>

        <div class="back">
            <p>{{ cardData.backText }}</p>
        </div>
    </div>-->

  <div
    class="grid-container"
    name="home-view"
    v-if="from === 'home'"
    v-on:click="flip(card)"
  >
    <!-- Button to delete card from database -->

      <button class="x" v-on:click="deleteCard(card.cardId)">X</button>

      <div class="head">
        <p>{{ card.cardId }} {{ card.username }}</p>
      </div>

      <div class="body">
        
        <div class="content" @click="flip(card)">
          <transition name="flip" @click="flip(card)" class="card-data">
            <p v-bind:key="card.flipped" class="session-card">
              {{ card.flipped ? card.backText : card.frontText }}
            </p>
          </transition>
        </div>
      </div>

      <div class="foot">
        <p>{{ card.tags }} {{ card.public }}</p>
      </div>
      <div class="edit">Edit</div>
      </div>
    
    
  

  <!-- Display for DeckView, shows all card properties except for public status -->
  <!--
    <div class="card" name="deck-view" v-if="from === 'deck'" v-on:click="flip(cardData)">
        <div class="head">
            <p>{{ cardData.cardId }} {{ cardData.username }}</p>-->
  <!-- Button to delete card from deck only -->
  <!--
            <button class="x" v-on:click="removeCardFromDeck(deckId, card.cardId)">X</button>
        </div>
        <div class="body">
            <p>{{ cardData.frontText }}</p>
        </div>
        <div class="foot" v-bind:class="{ front: !cardData.flipped }">
            <p>{{ cardData.tags }}</p>
        </div>

        <div class="back" v-bind:class="{ back: cardData.flipped }">
            <p>{{ cardData.backText }}</p>
        </div>
    </div>-->

  <div
    class="card"
    name="deck-view"
    v-if="from === 'deck'"
    v-on:click="flip(card)"
  >
    <div class="head">
      <p>{{ card.cardId }} {{ card.username }}</p>
      <!-- Button to delete card from deck only -->
      <button class="x" v-on:click="removeCardFromDeck(deckId, card.cardId)">
        X
      </button>
    </div>
    
      <div class="content" @click="flip(card)">
        <transition name="flip" @click="flip(card)" class="card-data">
          <p v-bind:key="card.flipped" class="session-card">
            {{ card.flipped ? card.backText : card.frontText }}
          </p>
        </transition>
      </div>
    
    <div class="foot" v-bind:class="{ front: !card.flipped }">
      <p>{{ card.tags }}</p>
    </div>
  </div>

  <!-- Display for Study Session, shows front and back text only -->

  <!-- <div class="card" name="session-view" v-if="from === 'session'" v-on:click="flip(card)">
        <div class="body">
            <button class="x" v-on:click="skipCard">X</button>
            <p>{{ card.frontText }}</p>
        </div>

        <div class="back">
            <p>{{ card.backText }}</p>
        </div>
    </div> -->

  <div
    class="card"
    name="session-view"
    v-if="from === 'session'"
    v-on:click="flip(card)"
  >
    <div class="body card-wrapper">
      <button class="x" v-on:click="skipCard">Xmen</button>
      <div class="content" @click="flip(card)">
        <transition name="flip" @click="flip(card)" class="card-data">
          <p v-bind:key="card.flipped" class="session-card">
            {{ card.flipped ? card.backText : card.frontText }}
          </p>
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
import CardService from "../services/CardService";
import DeckService from "../services/DeckService";

export default {
  props: ["card", "from", "deckId"],
  emits: ["refresh"],
  data() {
    return {
      cardData: {},
    };
  },
  methods: {
    flip(card) {
      // this.cardData.flipped = !this.cardData.flipped;
      const cardInfo = {
        cardId: card.cardId,
        flipped: !card.flipped,
        correct: card.correct,
      };
      this.$store.commit("UPDATE_CARD", cardInfo);
    },
    deleteCard(cardId) {
      if (
        confirm(
          "Are you sure you want to delete this card? This action cannot be undone."
        )
      ) {
        CardService.deleteCard(cardId)
          .then((response) => {
            if (response.status === 200) {
              this.$store.commit("SET_NOTIFICATION", {
                message: `Card has been deleted`,
                type: "success",
              });
              this.$emit("refresh");
              this.$router.push({ name: "home" });
            }
          })
          .catch((error) => {
            if (error.response) {
              this.$store.commit(
                "SET_NOTIFICATION",
                "Error deleting card. Response received was '" +
                  error.response.statusText +
                  "'."
              );
            } else if (error.request) {
              this.$store.commit(
                "SET_NOTIFICATION",
                "Error deleting card. Server could not be reached."
              );
            } else {
              this.$store.commit(
                "SET_NOTIFICATION",
                "Error deleting card. Request could not be created."
              );
            }
          });
      }
    },
    removeCardFromDeck(deckId, cardId) {
      if (confirm("Are you sure you want to remove this card from the deck?")) {
        DeckService.removeCardFromDeck(deckId, cardId)
          .then((response) => {
            if (response.status === 200) {
              this.$store.commit("SET_NOTIFICATION", {
                message: `Card has been removed from deck.`,
                type: "success",
              });
              this.$emit("refresh");
              this.$router.push({
                name: "deck-view",
                params: { deckId: deckId },
              });
            }
          })
          .catch((error) => {
            if (error.response) {
              this.$store.commit(
                "SET_NOTIFICATION",
                "Error removing card from deck. Response received was '" +
                  error.response.statusText +
                  "'."
              );
            } else if (error.request) {
              this.$store.commit(
                "SET_NOTIFICATION",
                "Error removing card from deck. Server could not be reached."
              );
            } else {
              this.$store.commit(
                "SET_NOTIFICATION",
                "Error removing card from deck. Request could not be created."
              );
            }
          });
      }
    },
  },
  created() {
    this.cardData = {
      cardId: this.card.cardId,
      username: this.card.username,
      frontText: this.card.frontText,
      backText: this.card.backText,
      cardTags: this.card.cardTags,
      public: this.card.public,
      flipped: false,
      correct: null,
    };
  },
};
</script>

<style scoped>
.grid-container {
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  grid-template-rows: 1fr 3fr 1fr;
  grid-template-areas:
    "head . x"
    "body body body"
    "foot . edit";


  border-radius: 15px;
  background-color: rgb(181, 194, 238);
  font-family: Arial, Helvetica, sans-serif;

  
    padding: 10px 10px;
    transition: all 0.5s ease;
}


.head {
    grid-area: head;
    background-color: white;
    margin: 10px;
    border-radius: 10px;
    
    
  
}

.x {
    grid-area: x;
    font-size: x-large;
    font-weight: bold;
    border: none;
    background: none;
    position: relative;
    color: black;
    right: -20px;
    top: -10px;
    padding: 10px 15px;
    opacity: .4;
    transition: all 0.5s ease;
    
}
.x:hover, .error {
    opacity: 1;
    transform: rotate(360deg);
    
}


.body {
  grid-area: body;
  text-align: center;
  justify-content: center;
  align-content: center;
  background-color: white;
  margin: 20px;
  border-radius: 10px;
  
}
.foot {
    grid-area: foot;
}
.edit {
    grid-area: edit;
}



.card-data {
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}

.flip-enter-active {
    transition: all 0.4s ease;
  }
  
  .flip-leave-active {
    display: none;
  }
  
  .flip-enter, .flip-leave {
    transform: rotateY(180deg);
    opacity: 0;
  
  }
</style>