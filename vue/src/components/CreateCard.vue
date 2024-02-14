<template>
  <div id="create-card">
    <h1>New Card Form</h1>
    
    <form v-on:submit.prevent="createCard">
    <input type="text" placeholder="Front Text" v-model="newCard.frontText"/> <br/><br/>
    <input type="text" placeholder="Back Text" v-model="newCard.backText"/> <br/><br/>
    <input type="text" placeholder="Tags" v-model="newCard.cardTags"/> <br/><br/>
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
