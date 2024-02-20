<template>
  <div class="home">
    <h1 id="my-decks">My Decks</h1>
    <div class="deck-display">
      <DecksList v-bind:deckList="deckList" />
    </div>
    <br />
    <div class="create-deck-button">
      <button class="create-deck-button">Create Deck
        <!-- <router-link class="router" v-bind:to="{ name: 'create-deck' }">Create New Deck</router-link> -->
      </button>
    </div>
    <br />
    <div class="card-display" v-show="showCards">
      <CardsList v-bind:cardList="cardList" from='home' @refresh="loadCards" />
    </div>
    <br />
    <!-- <div class="create-card-button"> -->
      <div class="btn-group">
            <button id="create" v-on:click.prevent="showCardForm" >Create Card</button>
            <CreateCard id="form" v-if="showAddCard" @refresh="loadCards"/>
        </div>
    <!-- </div> -->
  </div>
</template>

<script>
import DecksList from '../components/DecksList.vue';
import CardsList from '../components/CardsList.vue';
import DeckService from "../services/DeckService.js";
import CardService from "../services/CardService.js";
import CreateCard from "../components/CreateCard.vue";

export default {
  name: "home",
  emits: ['refresh'],
  data() {
    return {
      deckList: [],
      showAddCard: false,
    };
  },
  computed: {
    cardList() {
      return this.$store.state.currCards;
    },
    showCards() {
      return !this.$store.state.hideCards;
    }
  },
  components: {
    DecksList,
    CardsList,
    CreateCard
  },
  created() {
    // Hide cards so that they aren't visible as they flip back to
    // fronts
    this.$store.commit('SET_CARDS_HIDDEN', true);
    DeckService.getDecks().then((response) => {
      this.deckList = response.data;
    });
    this.loadCards()
  },
  methods: {
    refresh() {
      this.$emit('refresh')
    },
    showCardForm() {
            this.showAddCard=!this.showAddCard;
    },
    loadCards() {
      CardService.getCards().then((response) => {
        let cards = response.data;
        for (let i = 0; i < cards.length; i++) {
          cards[i].flipped = false;
          cards[i].completed = null;
        }
        this.$store.commit('SET_CARD_LIST', response.data);

        // Once done loading, show the cards
        this.$store.commit('SET_CARDS_HIDDEN', false)
      })
    },
    goToCreateCard() {
      this.$router.push({ name: 'create-card' });
    }
  }
};
</script>

<style>
body { 
   background-image:url("https://www.wallpaperboulevard.com/Images/product/cream-white-triangle-geometric-shapes-w-qsac-l.jpg");
     
    background-size: stretch;}

h1 {
  border-radius: 10px;
  font-family: Arial, Helvetica, sans-serif;
  text-align: center;
  color:black;

}
#my-decks {
  font-size:40px;
}

.create-deck-button {
  display: flex;
  align-items: center;
  justify-content: center;
  text-decoration: none;
  border-radius: 10px;
  border-color: #3498db;

}

.create-card-button {
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
  column-gap: 25px;
  row-gap: 10px;
  justify-content: center;
  align-items: center;
}

.card-display {
  display: flex;
  flex-wrap: wrap;
  margin: 20px;
  column-gap: 140px;
  row-gap: 50px;
  justify-content: center;
  /* align-items: center; */
}
</style>
