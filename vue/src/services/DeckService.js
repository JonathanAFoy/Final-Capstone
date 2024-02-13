import axios from 'axios';

export default {

    // DECK

    getDecks() {
        return axios.get('/decks');
    },

    getDeck(deckId) {
        return axios.get(`/decks/${deckId}`)
    },

    createDeck(deck) {
        return axios.post(`/create-deck`, deck);
    },

    deleteDeck(deckId) {
        return axios.delete(`/decks/${deckId}`);
    }
    
}