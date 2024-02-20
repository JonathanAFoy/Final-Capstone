<template>
  <div id="create-card">
    <h1>New Card Form</h1>
    
    <form v-on:submit.prevent="createCard">

<!--     <textarea type="text" placeholder="Front Text" v-model="newCard.frontText"/>
    <textarea type="text" placeholder="Back Text" v-model="newCard.frontText"/>
    <textarea type="text" placeholder="Tags" v-model="newCard.frontText"/> -->

    <input type="text" placeholder="Front Text" v-model="newCard.frontText"/> <br/><br/>
    <input type="text" placeholder="Back Text" v-model="newCard.backText"/> <br/><br/>
    <input type="text" placeholder="Tags" v-model="newCard.cardTags"/> <br/><br/>
    
    <button>Save</button> 
    </form>

  </div>
</template>
  
<script>
import CardService from '../services/CardService';
import DeckService from '../services/DeckService';
  
export default {
  props: [
    "deckId"
  ],
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
          if(!this.deckId){
          this.$router.push({name: "home"});
          } else {
            DeckService.addCard(this.deckId, response.data.cardId).then((resp) => {
              this.$store.commit('ADD_CARD_CURRDECK', response.data);
            })
          }
        }
      });
    },
  },
}
</script>
  
<style>
#create-card {
  border-radius: 15px;
  text-align: center;
  margin: 50px auto;
  max-width: 500px;
  background-color: #F7F9FF;
  padding: 30px;
  box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.2);
}
</style>
