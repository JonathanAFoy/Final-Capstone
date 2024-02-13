<template>
    <div class="decks">
      <router-link v-for="deck in decks" v-bind:key="deck.deckId"
        v-bind:to="{ name: 'DeckView', params: { id: deck.deckId } }">
        <div class="deck" v-bind:style="{}">{{ deck.title }}</div>
      </router-link>
    </div>
  </template>
  
  <script>
  export default {
    props: ['decks'],
  };
  </script>
  
  <style scoped>

  .decks { display: flex;
  flex-wrap: wrap;
  column-gap: 25px;
  row-gap: 25px;
  justify-content: flex-start;
  align-items: center;
}

.deck {
  min-width: 150px;
  border-radius: 10px;
  padding: 40px;
  text-align: center;
}


  </style>
  