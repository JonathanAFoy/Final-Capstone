<template>
    <div>
      <h1>New Deck Form</h1>
      {{ newDeck }}

      <form v-on:submit.prevent="createDeck">
      Title: <input type="text" v-model="newDeck.title"/> <br>
      Tags: <input type="text" v-model="newDeck.tags"/> <br>
      Description: <input type="text" v-model="newDeck.description"/> <br>
      <button>Save</button> 
    
      </form>
    </div>
  </template>
  
  <script>
  import DeckService from '../services/DeckService';
  
  export default {
      data() {
      return {
        newDeck: {},
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