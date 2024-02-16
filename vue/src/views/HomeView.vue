<template>
  <div class="home">
    <h1>My Decks</h1>
    <div class="deck-display">
      <DecksList v-bind:deckList="deckList" />
    </div>
    <br/>
    <div class="create-deck-button">
      <button class="create-deck-button">
        <router-link class="router" v-bind:to="{ name: 'create-deck' }">Create New Deck</router-link>
      </button>
    </div>
    <br/>
    <div class="card-display">
      <CardsList v-bind:cardList="cardList" from = 'home' />
    </div>
    <br/>
    <div class="create-card-button">
      <button  class="create-card-button">
        <router-link class="router" v-bind:to="{ name: 'create-card' }">Create New Card</router-link>
      </button>
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
    };
  },
  computed: {
    cardList() {
      return this.$store.state.currCards;
    }
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
      // this.cardList = response.data
      // response.data.forEach( card => {
      //   card.flipped = false;
      //   card.correct = null
      // })
      let cards = response.data;
      for (let i = 0; i < cards.length; i++ ) {
        cards[i].flipped = false;
        cards[i].completed = null;
      }
      this.$store.commit('SET_CARD_LIST', response.data);
    })
  },
};
</script>

<style>
html, body {
  background-color: #E6F4F1;
}

h1 {
  text-align: center;
}

.create-deck-button{
  display: flex;
  align-items: center;
  justify-content: center;
  text-decoration: none;
  border-radius: 10px;
  border-color: #3498db;
}

.create-card-button{
  display: flex;
  align-items: center;
  justify-content: center;
  text-decoration: none;
  border-radius: 10px;
  border-color: #3498db;
}

.router {
  display: flex;
  align-items: center;
  justify-content: center;
  text-decoration: none;
  padding: 12px;
  color: #3498db;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
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
