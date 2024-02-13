<template>
  <div class="home">
    <h1>My Cards and Decks</h1>
    <p>If you are seeing this, you are authenticated.</p>
    <br />
    <br />
    <br />

    <div class="deck-display">
      <div class="deck" v-for="deck in deckList" v-bind:key="deck.id">
        {{ deck.deckId }} <br />
        {{ deck.deckTitle }} <br />
        {{ deck.username }} <br />
      </div>
      <DecksList v-bind:deckList="deckList" />
    </div>

    <br />
    <br />
    <br />
    <router-link class="router" v-bind:to="{ name: 'create-deck' }"
      >Create New Deck</router-link>
<br />
<br />
<br />
  <div class="card-display">
    <div class="card" v-for="card in cardList" v-bind:key="card.id">
      {{ card.id }} <br />
      {{ card.title }} <br />
      {{ card.author }} <br />
    </div>
    <CardsList v-bind:cardList="cardList" />
  </div>
    <router-link class="router" v-bind:to="{ name: 'create-card' }"
      >Create New Card</router-link
    >
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
.home {
  column-gap: 25px;
  row-gap: 25px;
  align-items: center;
}

.deck-display {
  display: flex;
  flex-wrap: wrap;
  column-gap: 10px;
  row-gap: 10px;
  justify-content: center;
  align-items: center;
}

.deck {
  border-radius: 15px;
  padding: 10px;
  margin: 10px;
  border: 10px;
  width: 150px;
  background-color: rgb(129, 163, 255);
  text-align: center;
  font-family: Arial, Helvetica, sans-serif;
}
</style>