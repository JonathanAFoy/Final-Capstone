<template>
    <div>
      <h1>New Card Form</h1>
      {{ newCard }}

      <form v-on:submit.prevent="createCard">
      Front Text: <input type="text" v-model="newCard.frontText"/> <br>
      Back Text: <input type="text" v-model="newCard.backText"/> <br>
      Tags: <input type="text" v-model="newCard.cardTags"/> <br>
      <button>Save</button> 
    
      </form>
    </div>
  </template>
  
  <script>
  import CardService from '../services/CardService';
  
  export default {
      data() {
      return {
        newCard: {},
      };
    },
    methods: {
      createCard() {
        CardService.createCard(this.newCard).then((response) => {
          if (response.status === 201) {
            window.alert("Card Added!");
            this.$router.push({name: "home"});
          }
        });
      },
    },
  }
  </script>
  
  <style>
  
  </style>