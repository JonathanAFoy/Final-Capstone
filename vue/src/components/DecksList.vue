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
    <div class="deck-item" v-for="(deck, index) in deckList" v-bind:key="index">
      <div class="deck-item-container">
        <div class="deck-info-text">
          {{  deck.deckId  }}
        </div>
        <div class="deck-item-text">
          {{  deck.deckTitle  }}
        </div>
        <div class="deck-info-text">
          {{  deck.username  }}
        </div>
        <div class="deck-info-text">
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

  .decks { display: flex;
  flex-wrap: wrap;
  column-gap: 25px;
  row-gap: 25px;
  justify-content: flex-start;
  align-items: center;
}

.deck {
  min-width: 150px;
  border-radius: 10px;
  padding: 40px;
  text-align: center;
}


  </style>
  
