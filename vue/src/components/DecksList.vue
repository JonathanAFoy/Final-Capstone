<template>
    <div class="decks">
      <router-link v-for="deck in decks" v-bind:key="deck.id"
        v-bind:to="{ name: 'DeckView', params: { id: deck.id } }">
        <div class="deck">{{ deck.title }}</div>
      </router-link>
    </div>
  </template>
  
  <script>
  export default {
    props: ['decks'],
  };
  </script>
  
  <style scoped>

  </style>
  