<template>
    <div>
        <div class="board-actions">
            <router-link v-bind:to="{ name: 'home' }">Back to Decks</router-link>
        </div>
        <div class="header">
            <h1>{{ deck.deckTitle }}</h1>
            <!-- <router-link class="btn btn-submit" :to="{ name: 'AddCardView', params: { deckId: deck.deckId } }">Add
                New Card</router-link> -->
            <button class="btn btn-cancel deleteDeck" v-on:click="deleteDeck">Delete Board</button>
        </div>
        <div class="cards">
            <CardsList v-bind:cardList="cardList"/>
        </div>
    </div>
</template>
<script>
import CardsList from '../components/CardsList.vue'
import CardService from '../services/CardService';
import DeckService from '../services/DeckService';
export default {
    components: {
        CardsList
    },
    data() {
        return {
            cardList: []
        };
    },
    computed: {
        deck(){
            return this.$store.state.currDeck;
        },

        // planned() {
        //     return this.board.cards.filter(card => card.status === 'Planned');
        // },
        // inProgress() {
        //     return this.board.cards.filter(card => card.status === 'In Progress');
        // },
        // completed() {
        //     return this.board.cards.filter(card => card.status === 'Completed');
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
                this.cardList = response.data;
            })
            .catch(error => {
                if (error.response) {
                    if (error.response.status === 404) {
                        this.$store.commit('SET_NOTIFICATION',
                            "Error: Board " + deckId + " was not found. This board may have been deleted or you have entered an invalid board ID.");
                        this.$router.push({ name: 'home' });
                    } else {
                        this.$store.commit('SET_NOTIFICATION',
                            "Error getting board " + deckId + ". Response received was '" + error.response.statusText + "'.");
                    }
                } else if (error.request) {
                    this.$store.commit('SET_NOTIFICATION', "Error getting board. Server could not be reached.");
                } else {
                    this.$store.commit('SET_NOTIFICATION', "Error getting board. Request could not be created.");
                }
            });
    }
};
</script>
<style scoped>
.cards {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: 20px;
}

.header {
    display: flex;
    align-items: center;
}

.header h1 {
    flex-grow: 1;
}
</style>