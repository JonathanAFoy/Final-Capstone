<template>
  <div class>
    <!-- <CardsList v-bind:cardList="cardList" v-bind:deckId="$route.params.deckId" from = "session"/> -->
    <Card v-bind:card="currCard" from = "session" v-bind:deckId="$route.params.deckId" v-show="showCard"/>
    
    <br />
    <button v-on:click="nextCard">Next Card</button>
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



</style>