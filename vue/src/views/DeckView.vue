<template>
    <div>
        <div class="deck-actions">
            <router-link v-bind:to="{ name: 'home' }">Back to Decks</router-link>
        </div>
        <div class="header">
            <h1>{{ deck.deckTitle }}</h1>
        </div>
        <div>
            <button class="delete-deck" v-on:click="deleteDeck">Delete Deck</button>
        </div>
        <div class="cards">
            <CardsList v-bind:cardList="cardList"/>
        </div>
        <CreateCard v-bind:deckId="deck.deckId" />
    </div>
</template>

<script>
import CardsList from '../components/CardsList.vue'
import CardService from '../services/CardService';
import DeckService from '../services/DeckService';
import CreateCard from '../components/CreateCard.vue';
export default {
    components: {
        CardsList,
        CreateCard
    },
    data() {
        return {
            newCard: {},
            showAddCard: false
        };
    },
    computed: {
        deck(){
            return this.$store.state.currDeck;
        },
        cardList(){
            return this.$store.state.currCards;
        }

        // planned() {
        //     return this.deck.cards.filter(card => card.status === 'Planned');
        // },
        // inProgress() {
        //     return this.deck.cards.filter(card => card.status === 'In Progress');
        // },
        // completed() {
        //     return this.deck.cards.filter(card => card.status === 'Completed');
        // }
    },
    methods: {
        deleteDeck() {
            if (confirm("Are you sure you want to delete this deck and all associated cards? This action cannot be undone.")) {
                DeckService.deleteDeck(this.deck.deckId).then(response => {
                    if (response.status === 200) {
                        this.$store.commit('SET_NOTIFICATION',
                            {
                                message: `Deck has been deleted`,
                                type: 'success'
                            });
                        this.$router.push({ name: 'home' });
                    }
                }).catch(error => {
                    if (error.response) {
                        this.$store.commit('SET_NOTIFICATION',
                            "Error deleting deck. Response received was '" + error.response.statusText + "'.");
                    } else if (error.request) {
                        this.$store.commit('SET_NOTIFICATION',
                            "Error deleting deck. Server could not be reached.");
                    } else {
                        this.$store.commit('SET_NOTIFICATION',
                            "Error deleting deck. Request could not be created.");
                    }
                });
            }
        }
    },
    created() {
        let deckId = parseInt(this.$route.params.deckId);

        DeckService.getDeck(deckId)
            .then(response => {
            this.$store.commit('SET_CARD_LIST', response.data);
            })
            .catch(error => {
                if (error.response) {
                    if (error.response.status === 404) {
                        this.$store.commit('SET_NOTIFICATION',
                            "Error: Deck " + deckId + " was not found. This deck may have been deleted or you have entered an invalid deck ID.");
                        this.$router.push({ name: 'home' });
                    } else {
                        this.$store.commit('SET_NOTIFICATION',
                            "Error getting deck " + deckId + ". Response received was '" + error.response.statusText + "'.");
                    }
                } else if (error.request) {
                    this.$store.commit('SET_NOTIFICATION', "Error getting deck. Server could not be reached.");
                } else {
                    this.$store.commit('SET_NOTIFICATION', "Error getting deck. Request could not be created.");
                }
            });
    },
    // saveNewCard(newCard) {
    //     CardService.createCard(this.newCard).then((response) => {
    //     if (response.status === 201) {
    //       window.alert("Card Added!");
    //       this.$router.push({name: "home"});

    //     }
    //   });

    // }
};
</script>
<style scoped>
.cards {
    display: flex;
    align-items: center;
    justify-content: center;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px;
}

.header {
    display: flex;
    align-items: center;
    justify-content: center;
}

.header h1 {
    justify-content: center;
    flex-grow: 1;
}


div .delete-deck {
    display: flex;
    justify-content: right;
    align-items: right;
    text-align: right;
}

</style>