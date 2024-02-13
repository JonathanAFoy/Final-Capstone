import axios from 'axios';

export default {

    getDecks() {
        return axios.get('/deck');
    },

    getDeck(deckId) {
        return axios.get(`/deck/${deckId}`)
    },

    createDeck(deck) {
        return axios.post(`/deck/create`, deck);
    },

    updateDeck(deckId, deck){
        return axios.put(`/deck/${deckId}`, deck)
    },

    deleteDeck(deckId) {
        return axios.delete(`/deck/${deckId}`);
    }
    
}