<template>
    <!-- Display for HomeView, shows all card properties -->
    <div class="card" name="home-view" v-if="from === 'home'" v-on:click="flip">
        <div class="head">
            <p>{{ cardData.cardId }} {{ cardData.username }}</p>
            <!-- Button to delete card from database -->
            <button class="x" v-on:click="deleteCard(card.cardId)">X</button>
        </div>
        <div class="body">
            <p>{{ cardData.frontText }}</p>
        </div>
        <div class="foot">
            <p>{{ cardData.tags }} {{ cardData.public }}</p>
        </div>

        <div class="back">
            <p>{{ cardData.backText }}</p>
        </div>
    </div>


    <!-- Display for DeckView, shows all card properties except for public status -->
    <div class="card" name="deck-view" v-if="from === 'deck'" v-on:click="flip">
        <div class="head">
            <p>{{ cardData.cardId }} {{ cardData.username }}</p>
            <!-- Button to delete card from deck only -->
            <button class="x" v-on:click="removeCardFromDeck(deckId, card.cardId)">X</button>
        </div>
        <div class="body">
            <p>{{ cardData.frontText }}</p>
        </div>
        <div class="foot">
            <p>{{ cardData.tags }}</p>
        </div>

        <div class="back">
            <p>{{ cardData.backText }}</p>
        </div>
    </div>


    <!-- Display for Study Session, shows front and back text only -->
    <div class="card" name="session-view" v-if="from === 'session'" v-on:click="flip">
        <div class="body">
            <button class="x" v-on:click="skipCard">X</button>
            <p>{{ cardData.frontText }}</p>
        </div>

        <div class="back">
            <p>{{ cardData.backText }}</p>
        </div>
    </div>
</template>

<script>
import CardService from '../services/CardService';
import DeckService from '../services/DeckService';

export default {
    props: [
        'card', 'from', 'deckId'],
    data() {
        return {
            cardData: {}
        }
    },
    methods: {
        flip() {
            this.cardData.flipped = !this.cardData.flipped;
        },
        deleteCard(cardId) {
            if(confirm("Are you sure you want to delete this card? This action cannot be undone.")){
                CardService.deleteCard(cardId).then((response) => {
                    if (response.status === 200) {
                        this.$store.commit("SET_NOTIFICATION", {
                            message: `Card has been deleted`,
                            type: "success",
                        });
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
            if(confirm("Are you sure you want to remove this card from the deck?")){
                DeckService.removeCardFromDeck(deckId, cardId).then((response) => {
                    if (response.status === 200) {
                        this.$store.commit("SET_NOTIFICATION", {
                            message: `Card has been removed from deck.`,
                            type: "success",
                        });
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

<style></style>