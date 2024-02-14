<template>
  <div class="cards-container" v-for="(card, index) in cardList" v-bind:key="index">
    <div class="card-item">
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
    </div>
  </div>
</template>
  
<script>
export default {
  props: ['cardList'],
};
</script>

<style scoped>
.cards-container {
  display: flex;
  flex-wrap: wrap;
  column-gap: 25px;
  row-gap: 25px;
  justify-content: flex-start;
  align-items: center;
}

.card-item {
  justify-content: center;
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
</style>
