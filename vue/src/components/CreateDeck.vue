<template>
    <div id="create-deck">
      <h1>New Deck Form</h1>
      <form v-on:submit.prevent="createDeck">
      <input type="text" placeholder="Title" v-model="newDeck.deckTitle"/> <br/><br/>
      <input type="text" placeholder="Tags" v-model="newDeck.deckTags"/> <br/><br/>
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
    #create-deck {
    text-align: center;
    margin: 50px auto;
    max-width: 500px;
    background-color: #F7F9FF;
    padding: 30px;
    box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.2);
  }

/* .btns {
  background-color: #3498db;
} */
  </style>