<template>
  <div>
    <!-- <CardsList v-bind:cardList="cardList" v-bind:deckId="$route.params.deckId" from = "session"/> -->
    <Card v-bind:card="currCard" from = "session" v-bind:deckId="$route.params.deckId" />
    <button v-on:click="nextCard">Next Card</button>
  </div>
</template>

<script>

// import CardService from '../services/CardService';
import Card from '../components/Card.vue';

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
    this.cardIndex++;
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
    
    // CardService.getCards().then((response) => {
    //   this.cardList = response.data
    // })
  },
}
</script>

<style>

</style>