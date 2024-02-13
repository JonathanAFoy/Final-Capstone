<!-- <template>
    <div class="decks">
      <router-link v-for="deck in deckList" v-bind:key="deck.id"
        v-bind:to="{ name: 'DeckView', params: { id: deck.id } }">
        <div class="deck">{{ deck.title }}</div>
      <router-link v-for="deck in decks" v-bind:key="deck.deckId"
        v-bind:to="{ name: 'DeckView', params: { id: deck.deckId } }">
        <div class="deck" v-bind:style="{}">{{ deck.title }}</div>
      </router-link>
    </div>
  </template> -->

  <template>
    <div class="decks-container" v-for="(deck, index) in deckList" v-bind:key="index">
      <div class="deck-item">
        <div class="deck-info-text">
          {{  deck.deckId  }}
        <br />
       
          {{  deck.deckTitle  }}
        <br />
        
          {{  deck.username  }}
        <br />
       
          {{  deck.deckTags  }}
        </div>
        <br/>
        <br/>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: ['deckList'],
  };
  </script>
  
  <style scoped>

  .decks-container { 
  display: flex;
  flex-wrap: wrap;
  column-gap: 25px;
  row-gap: 25px;
  justify-content: center;
  text-align: center;
}

.deck-item {
  display: flex;
  border-radius: 15px;
  padding: 10px;
  margin: 10px;
  border: 10px;
  width: 150px;
  background-color: rgb(129, 163, 255);
  font-family: Arial, Helvetica, sans-serif;
  justify-content: center;
}



  </style>
  
