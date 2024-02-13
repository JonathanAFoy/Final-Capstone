<!-- <template>
    <div class="decks">
      <router-link v-for="deck in deckList" v-bind:key="deck.id"
        v-bind:to="{ name: 'DeckView', params: { id: deck.id } }">
        <div class="deck">{{ deck.title }}</div>
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

  </style>
  
