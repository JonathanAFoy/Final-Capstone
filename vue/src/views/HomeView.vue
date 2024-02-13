<template>
  <div class="home">
    <h1>My Cards and Decks</h1>
    <p>If you are seeing this, you are authenticated.</p>
    <br>
    <router-link v-bind:to="{name: 'create-deck'}">Create New Deck</router-link>

    <div class="deck" v-for="deck in deckList" v-bind:key="deck.id">
    
    {{ deck.id }} <br />
    {{ deck.title }} <br />
    {{ deck.author }} <br />
    
    </div>
    <br />
    <br />
    <br />

    <router-link v-bind:to="{name: 'create-card'}">Create New Card</router-link>
    <div class="card" v-for="card in cardList" v-bind:key="card.id">

      {{ card.id }} <br />
      {{ card.title }} <br />
      {{ card.author }} <br />
    
    </div>

  </div>
</template>

<script>
import DecksList from '../components/DecksList.vue';
import DeckService from "../services/DeckService.js"
import CardService from "../services/CardService.js"

export default {
  
  name: "home",
  data() {
    return {
      deckList: [],
    };
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