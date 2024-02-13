<template>
  <div class="home">
    <h1>My Cards and Decks</h1>
    <p>If you are seeing this, you are authenticated.</p>
    <br>
    <router-link v-bind:to="{name: 'create-deck'}">Create New Deck</router-link>
    <!-- <div class="deck" v-for="deck in deckList" v-bind:key="deck.id">
    
    {{ deck.deckId }} <br />
    {{ deck.deckTitle }} <br />
    {{ deck.username }} <br />
    
    </div> -->

    <div class="decks">
      <DecksList v-bind:deckList="deckList" />
    </div>

    <br />
    <br />
    <br />

    <router-link v-bind:to="{name: 'create-card'}">Create New Card</router-link>
    <!-- <div class="card" v-for="card in cardList" v-bind:key="card.id">

      {{ card.id }} <br />
      {{ card.title }} <br />
      {{ card.author }} <br />
    
    </div> -->

    <div class="cards">
      <CardsList v-bind:cardList="cardList" />
    </div>

  </div>
</template>

<script>
import DecksList from '../components/DecksList.vue';
import CardsList from '../components/CardsList.vue';
import DeckService from "../services/DeckService.js";
import CardService from "../services/CardService.js";

export default {
  
  name: "home",
  data() {
    return {
      deckList: [],
      cardList: []
    };
  },
  components: {
    DecksList,
    CardsList
  },
  created() {
    DeckService.getDecks().then((response) => {
      this.deckList = response.data;
    });
  },
};
</script>

<style>

</style>