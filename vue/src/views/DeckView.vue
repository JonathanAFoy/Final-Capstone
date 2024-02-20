<template>
    <div>
        <br />
        <br />
        <div class="btn-group">
            <button id="back-to-decks">
                <router-link v-bind:to="{ name: 'home' }">Back to Decks</router-link>
            </button>

            <button id="study-session">
                <router-link v-bind:to="{ name: 'study-session' }">Study Session</router-link>
            </button>

            <button id="delete-deck" v-on:click="deleteDeck">
                Delete Deck
            </button>
        </div>
        <div class="header">
            <h1>{{ deck.deckTitle }}</h1>
        </div>
        <div class="tags">
            <h2>({{ deck.deckTags }})</h2>
        </div>

        <div class="card-display">
            <CardsList v-bind:cardList="cardList" v-bind:deckId="deck.deckId" from='deck' @refresh="loadData" />
        </div>
        <div class="btn-group">
            <button id="create" v-on:click.prevent="showCardForm">Create Card</button>
            <button id="create" v-on:click.prevent="showDeckForm">Edit Deck</button> 
        </div>
        <div class="btn-group">
            <CreateCard id="form" v-if="showAddCard" v-bind:deckId="deck.deckId" />
            <CreateDeck id="form" v-if="showEditDeck" v-bind:deck="deck" />
        </div>
    </div>
</template>

<script>
import CardsList from "../components/CardsList.vue";
import CardService from "../services/CardService";
import DeckService from "../services/DeckService";
import CreateCard from "../components/CreateCard.vue";
import CreateDeck from "../components/CreateDeck.vue";

export default {

    components: {
        CardsList,
        CreateCard,
        CreateDeck
    },
    data() {
        return {
            newCard: {},
            showAddCard: false,
            showEditDeck: false,
            from: "deck"
        };
    },
    computed: {
        deck() {
            return this.$store.state.currDeck;
        },
        cardList() {
            return this.$store.state.currCards;
        },

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
        showCardForm() {
            this.showAddCard=!this.showAddCard;
        },
        showDeckForm() {
            this.showEditDeck = !this.showEditDeck
        },
        loadData() {
            DeckService.getCardsForDeck(this.deck.deckId)
                .then((response) => {
                    let cards = response.data;
                    for (let i = 0; i < cards.length; i++) {
                        cards[i].flipped = false;
                        cards[i].completed = null;
                    }
                    this.$store.commit('SET_CARD_LIST', cards);

                    // Show cards
                    this.$store.commit('SET_CARDS_HIDDEN', false);
                })
        },
        deleteDeck() {
            if (
                confirm(
                    "Are you sure you want to delete this deck and all associated cards? This action cannot be undone."
                )
            ) {
                DeckService.deleteDeck(this.deck.deckId)
                    .then((response) => {
                        if (response.status === 200) {
                            this.$store.commit("SET_NOTIFICATION", {
                                message: `Deck has been deleted`,
                                type: "success",
                            });
                            this.$router.push({ name: "home" });
                        }
                    })
                    .catch((error) => {
                        if (error.response) {
                            this.$store.commit(
                                "SET_NOTIFICATION",
                                "Error deleting deck. Response received was '" +
                                error.response.statusText +
                                "'."
                            );
                        } else if (error.request) {
                            this.$store.commit(
                                "SET_NOTIFICATION",
                                "Error deleting deck. Server could not be reached."
                            );
                        } else {
                            this.$store.commit(
                                "SET_NOTIFICATION",
                                "Error deleting deck. Request could not be created."
                            );
                        }
                    });
            }
        },
    },
    created() {
        let deckId = parseInt(this.$route.params.deckId);

        // DeckService.getDeck(deckId).then( resp => {
        //     this.$store.commit('SET_DECK', resp.data);
        // })


        // Hide cards so that they aren't visible as they flip back to
        // fronts
        this.$store.commit('SET_CARDS_HIDDEN', true);
        DeckService.getCardsForDeck(deckId)
            .then((response) => {
                let cards = response.data;
                for (let i = 0; i < cards.length; i++) {
                    cards[i].flipped = false;
                    cards[i].completed = null;
                }
                this.$store.commit('SET_CARD_LIST', cards);
            })
            .catch((error) => {
                if (error.response) {
                    if (error.response.status === 404) {
                        this.$store.commit(
                            "SET_NOTIFICATION",
                            "Error: Deck " +
                            deckId +
                            " was not found. This deck may have been deleted or you have entered an invalid deck ID."
                        );
                        this.$router.push({ name: "home" });
                    } else {
                        this.$store.commit(
                            "SET_NOTIFICATION",
                            "Error getting deck " +
                            deckId +
                            ". Response received was '" +
                            error.response.statusText +
                            "'."
                        );
                    }
                } else if (error.request) {
                    this.$store.commit(
                        "SET_NOTIFICATION",
                        "Error getting deck. Server could not be reached."
                    );
                } else {
                    this.$store.commit(
                        "SET_NOTIFICATION",
                        "Error getting deck. Request could not be created."
                    );
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
    font-family: Arial, Helvetica, sans-serif;
}

.header {
    display: flex;
    align-items: center;
    justify-content: center;
    font-family: Arial, Helvetica, sans-serif;
    font-size: x-large;
    font-weight: bold;
}

.tags {
    display: flex;
    align-items: center;
    justify-content: center;
}

.header h1 {
    justify-content: center;
    flex-grow: 1;
}

.tags {
    margin-top: -30px;
}

/* .delete-deck {
    display: flex;
    justify-content: right;
    align-items: right;
    text-align: right;
    
} */

.btn-group {
    display: flex;
    justify-content: space-evenly;
    font-family: Arial, Helvetica, sans-serif;
    font-weight: bold;
    text-align: center;
    text-decoration: none;
    border: none;
    margin: 10px;
}

button {
    border-radius: 10px;
    height: 40px;
    border:none;
}

button:hover{
    transform: scale(1.1);
    transition: ease 0.3s;
  }

#delete-deck:hover {
    cursor: pointer;
}

#study-session {
    text-decoration: none;
    font-size: large;
    align-items: center;
    background-color: rgba(74, 167, 110, 0.765);
    color: white;
}

#back-to-decks {
    text-decoration: none;
    font-size: large;
    align-items: center;
    background-color: rgb(224, 205, 38);
    color: white;
}

#delete-deck {
    align-items: center;
    font-size: large;
    background-color: rgba(182, 0, 0, 0.765);
    font-weight: bold;
    color: white;
    width: 140px;
}

#create {
    align-items: center;
    margin-top: 50px;
    font-size: large;
    background-color: rgba(74, 167, 110, 0.765);
    font-weight: bold;
    color: white;
    width: 120px;
    height: 40px;
    
}

.card-display {
  display: flex;
  flex-wrap: wrap;
  margin: 20px;
  column-gap: 140px;
  row-gap: 50px;
  justify-content: center;
  /* align-items: center; */
}

.forms {
    display: flex;
    justify-content:space-around;
}

a {
    text-decoration: none;
    color: white; 
    font-family:Arial, Helvetica, sans-serif;
    font-weight: bold;
}
</style>