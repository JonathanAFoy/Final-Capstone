<template>
    <!-- Display for HomeView, shows all card properties -->
    <div class="card" name="home-view" v-if="from === 'home'" v-on:click="flip(card)">
        <div class="scene">
            <div class="card" :class="{ 'is-flipped': card.flipped }">
                <div class="card-face front">
                    <div class="head">
                        <div>{{ card.cardId }} {{ card.username }}</div>
                    </div>
                    <div class="x-container">
                        <button class="x" v-on:click="deleteCard(card.cardId)">X</button>
                    </div>
                    <div class="card-face-data">{{ card.frontText }}</div>
                    <div class="foot">
                        <div>{{ card.cardTags }} {{ card.public }}</div>
                    </div>
                </div>
                <div class="card-face back">
                    <div class="head">
                        <div>{{ card.cardId }} {{ card.username }}</div>
                    </div>
                    <div class="x-container">
                        <button class="x" v-on:click="deleteCard(card.cardId)">X</button>
                    </div>
                    <div class="card-face-data">{{ card.backText }}</div>
                    <div class="foot">
                        <div>{{ card.cardTags }} {{ card.public }}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- Display for DeckView, shows all card properties except for public status -->
    <div class="card" name="deck-view" v-if="from === 'deck'" v-on:click="flip(card)">
        <div class="scene">
            <div class="card" :class="{ 'is-flipped': card.flipped }">
                <div class="card-face front">
                    <div class="head">
                        <div>{{ card.cardId }} {{ card.username }}</div>
                    </div>
                    <div class="x-container">
                        <button class="x" v-on:click="removeCardFromDeck(deckId, card.cardId)">X</button>
                    </div>
                    <div class="card-face-data">{{ card.frontText }}</div>
                    <div class="foot">
                        <div>{{ card.cardTags }}</div>
                    </div>
                </div>
                <div class="card-face back">
                    <div class="head">
                        <div>{{ card.cardId }} {{ card.username }}</div>
                    </div>
                    <div class="x-container">
                        <button class="x" v-on:click="deleteCard(card.cardId)">X</button>
                    </div>
                    <div class="card-face-data">{{ card.backText }}</div>
                    <div class="foot">
                        <div>{{ card.cardTags }} </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

     <!-- Display for Study Session, shows front and back text only -->
    <div class="card" name="session-view" v-if="from === 'session'" v-on:click="flip(card)">
        <div class="scene">
            <div class="card" :class="{ 'is-flipped': card.flipped }">
                <div class="card-face front">
                    <div class="head">
                        <div></div>
                    </div>
                    <div class="x-container">
                        <!-- <button class="x" v-on:click="removeCardFromDeck(deckId, card.cardId)">X</button> -->
                    </div>
                    <div class="card-face-data">{{ card.frontText }}</div>
                    <div class="foot">
                        <div></div>
                    </div>
                </div>
                <div class="card-face back">
                    <div class="head">
                        <div></div>
                    </div>
                    <div class="x-container">
                        <!-- <button class="x" v-on:click="deleteCard(card.cardId)">X</button> -->
                    </div>
                    <div class="card-face-data">{{ card.backText }}</div>
                    <div class="foot">
                        <div></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import CardService from '../services/CardService';
import DeckService from '../services/DeckService';

export default {
    props: [
        'card', 'from', 'deckId'],
    emits: ['refresh'],
    data() {
        return {
            cardData: {}
        }
    },
    methods: {
        flip(card) {
            // this.cardData.flipped = !this.cardData.flipped;
            const cardInfo = {
                cardId: card.cardId,
                flipped: !card.flipped,
                correct: card.correct
            }
            this.$store.commit('UPDATE_CARD', cardInfo)
        },
        deleteCard(cardId) {
            if (confirm("Are you sure you want to delete this card? This action cannot be undone.")) {
                CardService.deleteCard(cardId).then((response) => {
                    if (response.status === 200) {
                        this.$store.commit("SET_NOTIFICATION", {
                            message: `Card has been deleted`,
                            type: "success",
                        });
                        this.$emit('refresh')
                        this.$router.push({ name: "home" });
                    }
                })
                    .catch((error) => {
                        if (error.response) {
                            this.$store.commit(
                                "SET_NOTIFICATION",
                                "Error deleting card. Response received was '" +
                                error.response.statusText +
                                "'."
                            );
                        } else if (error.request) {
                            this.$store.commit(
                                "SET_NOTIFICATION",
                                "Error deleting card. Server could not be reached."
                            );
                        } else {
                            this.$store.commit(
                                "SET_NOTIFICATION",
                                "Error deleting card. Request could not be created."
                            );
                        }
                    });
            }
        },
        removeCardFromDeck(deckId, cardId) {
            if (confirm("Are you sure you want to remove this card from the deck?")) {
                DeckService.removeCardFromDeck(deckId, cardId).then((response) => {
                    if (response.status === 200) {
                        this.$store.commit("SET_NOTIFICATION", {
                            message: `Card has been removed from deck.`,
                            type: "success",
                        });
                        this.$emit('refresh')
                        this.$router.push({ name: "deck-view", params: { deckId: deckId } });
                    }
                })
                    .catch((error) => {
                        if (error.response) {
                            this.$store.commit(
                                "SET_NOTIFICATION",
                                "Error removing card from deck. Response received was '" +
                                error.response.statusText +
                                "'."
                            );
                        } else if (error.request) {
                            this.$store.commit(
                                "SET_NOTIFICATION",
                                "Error removing card from deck. Server could not be reached."
                            );
                        } else {
                            this.$store.commit(
                                "SET_NOTIFICATION",
                                "Error removing card from deck. Request could not be created."
                            );
                        }
                    });
            }
        }
    },
    created() {
        this.cardData = {
            cardId: this.card.cardId,
            username: this.card.username,
            frontText: this.card.frontText,
            backText: this.card.backText,
            cardTags: this.card.cardTags,
            public: this.card.public,
            flipped: false,
            correct: null
        }
    }

}
</script>

<style scoped>


.scene {
    width: 200px;
    height: 180px;
    margin: 40px 0;
    perspective: 600px;

}

.card {
    width: 100%;
    height: 100%;
    border-radius: 15px;
    transition: transform 1s;
    transform-style: preserve-3d;
    cursor: pointer;
    position: relative;
    font-family: Arial, Helvetica, sans-serif;
}

.card.is-flipped {
    transform: rotateY(180deg);
}

.card-face {
    border-radius: 15px;
    position: absolute;
    width: 100%;
    height: 100%;
    line-height: 260px;
    color: white;
    text-align: center;
    font-size: 20px;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
}


.card-face-data {
    position: absolute;
    width: 100%;
    height: 65%;
    line-height: 260px;
    /* color: white; */
    text-align: center;
    font-size: 20px;
    display: flex;
    justify-content: center;
    align-items: center;

}

.card-face.front {
    background-color: rgb(129, 163, 255);
    color: #000000;
}

.card-face.back {
    /* background-color: blue; */
    background-color: rgb(129, 163, 255);
    transform: rotateY(180deg);
    color: #000000;
}

.x-container {
    height: 15%;
    width: 100%;
    background-color: rgb(129, 163, 255);
    display: flex;
    justify-content: flex-start;
    align-items: center;

    border-radius: 15px;
}

.x {
    position: absolute;
    height: 10%;
    margin: 4px;
    border-radius: 10px;
    justify-content: right !important;
    align-items: right !important;
    text-align: right !important;
    ;
    /* background-color: rgb(129, 163, 255); */

}

.head {
    position: absolute;
    width: 100%;
    height: 15%;
    margin-top: 2px;
    font-size: 16px;
    /* text-align: center; */
    font-family: Arial, Helvetica, sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
}

.foot {
    font-size: 16px;
}
</style>