<template>
    <div>
      <h1>New Deck Form</h1>
      {{ newDeck }}

      <form v-on:submit.prevent="createDeck">
      Title: <input type="text" v-model="newDeck.deckTitle"/> <br>
      Tags: <input type="text" v-model="newDeck.deckTags"/> <br>
      <div class="btns">
        <input type="submit" class="regular-btn" />
      </div>

      </form>
    </div>
  </template>
  
  <script>
  import DeckService from '../services/DeckService';
  
  export default {
      data() {
      return {
        newDeck: {
        },
      };
    },
    methods: {
      createDeck() {
        DeckService.createDeck(this.newDeck).then((response) => {
          if (response.status === 201) {
            window.alert("Deck Added!");
            this.$router.push({name: "home"});
          }
        });
      },
    },
  }
  </script>
  
  <style>
  
  </style>