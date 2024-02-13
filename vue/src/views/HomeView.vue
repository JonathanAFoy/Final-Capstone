<template>
  <div class="home">
    <h1>My Cards and Decks</h1>
    <p>If you are seeing this, you are authenticated.</p>
<<<<<<< HEAD
    <br>
    <router-link v-bind:to="{name: 'create-deck'}">Create New Deck</router-link>
    <!-- <div class="deck" v-for="deck in deckList" v-bind:key="deck.id">
    
    {{ deck.deckId }} <br />
    {{ deck.deckTitle }} <br />
    {{ deck.username }} <br />
    
    </div> -->

    <div class="decks">
      <DecksList v-bind:deckList="deckList" />
=======
    <br />
    <br />
    <br />

    <div class="deck-display">
      <div class="deck" v-for="deck in deckList" v-bind:key="deck.id">
        {{ deck.deckId }} <br />
        {{ deck.deckTitle }} <br />
        {{ deck.username }} <br />
      </div>
>>>>>>> 9aca2625da27111e04feba4ba7a7123508a51722
    </div>

    <br />
    <br />
    <br />
<<<<<<< HEAD

    <router-link v-bind:to="{name: 'create-card'}">Create New Card</router-link>
    <!-- <div class="card" v-for="card in cardList" v-bind:key="card.id">

      {{ card.id }} <br />
      {{ card.title }} <br />
      {{ card.author }} <br />
    
    </div> -->

    <div class="cards">
      <CardsList v-bind:cardList="cardList" />
=======
    <router-link class="router" v-bind:to="{ name: 'create-deck' }"
      >Create New Deck</router-link>
<br />
<br />
<br />
    
    <div class="card" v-for="card in cardList" v-bind:key="card.id">
      {{ card.id }} <br />
      {{ card.title }} <br />
      {{ card.author }} <br />
>>>>>>> 9aca2625da27111e04feba4ba7a7123508a51722
    </div>

    <router-link class="router" v-bind:to="{ name: 'create-card' }"
      >Create New Card</router-link
    >
  </div>
</template>

<script>
<<<<<<< HEAD
import DecksList from '../components/DecksList.vue';
import CardsList from '../components/CardsList.vue';
=======
import DecksList from "../components/DecksList.vue";
>>>>>>> 9aca2625da27111e04feba4ba7a7123508a51722
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

.router {



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