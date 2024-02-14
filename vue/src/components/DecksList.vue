<template>
  <div class="decks-container" v-for="(deck, index) in deckList" v-bind:key="index" >
    <div class="deck-item" v-on:click="showDeck(deck)">
      <div class="deck-info-text">
        <h2>{{  deck.deckTitle  }}</h2>
        <h4>Created by {{  deck.username  }}</h4>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['deckList'],
  methods: {
    showDeck(deck){
      this.$store.commit('SET_DECK', deck);
      this.$router.push({ name: 'deck-view', params: { deckId: deck.deckId } });
    }
}
};

</script>

<style scoped>
h2{
  font-size: 18px;
}

h4{
  font-size: 12px;
}

.decks-container { 
  display: flex;
  flex-wrap: wrap;
  column-gap: 25px;
  row-gap: 25px;
  justify-content: center;
  text-align: center;
}

.deck-item {
  display: flex;
  border-radius: 15px;
  padding: 10px;
  margin: 10px;
  border: 10px;
  width: 150px;
  background-color: rgb(129, 163, 255);
  font-family: Arial, Helvetica, sans-serif;
  justify-content: center;
}
</style>
