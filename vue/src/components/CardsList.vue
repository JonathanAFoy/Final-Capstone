<template>
  <div class="cards-container" v-for="(card, index) in cardList" v-bind:key="index">
    <Card v-bind:card="card" v-bind:from="from" v-bind:deckId="deckId" @refresh="refresh"/>
    <!-- <div class="card-item">
     
      <div class="card-info-text">
        {{  card.cardId  }}
        <br/>
        {{  card.username  }}
        <br/>
        {{  card.frontText  }}
        <br/>
        {{  card.backText  }}
        <br/>
        {{  card.cardTags  }}
      </div>
      <button class="kill">X</button>
    </div> -->
    
  </div>
</template>
  
<script>
import Card from '../components/FlipCard.vue';
import CardService from '../services/CardService';

export default {
  props: ['cardList', 'from', 'deckId'],
  emits: ['refresh'],
  components: {
    Card 
  },
  methods: {
    refresh() {
      this.$emit('refresh')
    }
  }
};
</script>

<style scoped>
/* .cards-container {
  display: flex;
  flex-wrap: wrap;
  column-gap: 25px;
  row-gap: 25px;
  justify-content: flex-start;
  align-items: center;
}

.card-item {
  justify-content: space-between;
  display: flex;
  border-radius: 15px;
  padding: 10px;
  margin: 10px;
  border: 10px;
  width: 150px;
  background-color: rgb(129, 163, 255);
  text-align: center;
  font-family: Arial, Helvetica, sans-serif;
}
.kill {
 height: 10%;
 border-radius: 10px;
 justify-content: right !important;
 align-items: right !important;
 text-align: right !important;

} */
</style>
