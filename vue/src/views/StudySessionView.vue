<template>
  <div>
    <CardsList v-bind:cardList="cardList" />
  </div>
</template>

<script>
import CardsList from '../components/CardsList.vue';
import CardService from '../services/CardService';
export default {
    name: "home",
  data() {
    return {
      
      cardList: [],
    };
    
},
components: {
    CardsList,
},
created() {
    
    CardService.getCards().then((response) => {
      this.cardList = response.data
    })
  },
}
</script>

<style>

</style>