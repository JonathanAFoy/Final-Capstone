<template>
    <div class="card-item" v-for="(card, index) in cardList" v-bind:key="index">
      <div class="card-item-container">
       
        <div class="card-info-text">
          {{  card.cardId  }}
        </div>
        <div class="card-info-text">
          {{  card.username  }}
        </div>
        <div>
            {{  card.frontText  }}
        </div>
        <div>
            {{  card.backText  }}
        </div>
        <div class="card-info-text">
          {{  card.cardTags  }}
        </div>
        <br/>
        <br/>
        <br/>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: ['cardList'],
  };
  </script>
  
  <style scoped>

  </style>
  
