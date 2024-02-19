<template>
  <div class="card">
    <Card v-bind:card="currCard" from = "session" v-bind:deckId="$route.params.deckId" v-show="showCard"/>
  </div>
    <br />
  <div class="next-card-button">
    <button class="next-card-button" v-on:click="nextCard">Next Card</button>
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
computed: {
  showCard() {
    return !this.$store.state.hideCards;
  }
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
    
  }
},
computed: {
  cardList() {
    return this.$store.state.currCards;
  },
  currCard() {
    return this.cardList[this.cardIndex];
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

</style>