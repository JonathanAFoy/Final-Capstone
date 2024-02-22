<template>
  <div class="card">
    <Card v-bind:card="currCard" from="session" v-bind:deckId="$route.params.deckId" v-show="showCard" />
  </div>
  <br />
  <br />
  <div class="count">
    <h2>
      {{ correctCount }}/{{ this.cardList.length }} Correct
    </h2>
  </div>
  <div class="next-card-button">
    <button class="next-card-button" v-on:click="nextCard" v-show="cardIndex < cardList.length - 1">Next Card</button>
    <a href="#" @click.prevent="show()">End Session</a>
  </div>
  <div class="popup" id="endSession" ref="endSession">
    <p>Session Completed</p>
    <p>You correctly answered {{ correctCount }} out of {{ this.cardList.length }} cards.</p>
    <a href="#" @click.prevent="toDeckView">Back to Deck</a>
  </div>
</template>

<script>

// import CardService from '../services/CardService';
import Card from '../components/FlipCard.vue';

export default {
  name: "home",
  data() {
    return {
      cardIndex: 0,
      // cardList: [],
    };

  },

  components: {
    Card
  },

  methods: {
    nextCard() {
      this.$store.commit('UPDATE_CARD', {
        cardId: this.currCard.cardId,
        flipped: false,
        correct: this.currCard.correct
      });

      // Hide cards as we move to next card
      this.$store.commit('SET_CARDS_HIDDEN', true);
      this.cardIndex++;

      // Show the cards
      this.$store.commit('SET_CARDS_HIDDEN', false);

    },

    show() {
      return this.$refs.endSession.style.display = 'block';
    },

    toDeckView() {
      this.$router.push({name: "deck-view", params: {deckId: this.$route.params.deckId}})
    }
  },

  computed: {
    showCard() {
      return !this.$store.state.hideCards;
    },
    cardList() {
      return this.$store.state.currCards;
    },
    currCard() {
      return this.cardList[this.cardIndex];
    },
    correctCount() {
      let count = 0;
      for (let i = 0; i < this.cardList.length; i++) {
        if (this.cardList[i].correct == true) {
          count++;
        }
      } return count;
    }
  },
  created() {
    // Hide cards while flipping all back to front
    this.$store.commit('SET_CARDS_HIDDEN', true);
    this.$store.commit('SET_ALL_CARDS_TO_FRONT');
    // Show cards
    this.$store.commit('SET_CARDS_HIDDEN', false);
  },
}
</script>

<style>
.card {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 5%;
  margin-bottom: 1%;
}

.count {
  display: flex;
  align-items: center;
  justify-content: center;
}

.next-card-button {
  display: flex;
  align-items: center;
  justify-content: center;
  text-decoration: none;
  font-size: large;
  padding: 12px;
  color: #3498db;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.popup {
  display: none;
  text-align: center;
  border-radius: 20px;
  position: fixed;
  padding: 10px;
  width: 280px;
  left: 50%;
  margin-left: -150px;
  height: 180px;
  top: 50%;
  margin-top: -100px;
  background: #7b68cec5;
  z-index: 20;
}

#endSession:before {
  position: absolute;
  content: "";
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  background: #7b68cee0;
  z-index: -1;
}

p {
  color: white;
  font-size: larger;
}

a {
  color: white;
  text-decoration: none;
  border-outline: black;
}
</style>