<template>
  <div class="home">
    <h1>My Cards and Decks</h1>
    <p>If you are seeing this, you are authenticated.</p>
    <br />
    <br />
    <br />
    <br />

    <div class="deck-display">
      
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
    CardService.getCards().then((response) => {
      this.cardList = response.data
    })
  },
};
</script>

<style>
html, body {
  background-color: #E6F4F1;
}

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

.card-display {
  display: flex;
  flex-wrap: wrap;
  column-gap: 10px;
  row-gap: 10px;
  justify-content: center;
  align-items: center;
}

</style>