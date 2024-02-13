import axios from 'axios';

export default {

    getCards() {
        return axios.get(`/card`)
    },

    getCard(cardId) {
        return axios.get(`/card/${cardId}`);
    },

    createCard(card) {
        return axios.post(`/card/create`, card);
    },

    updateCard(card) {
        return axios.put(`/card/${card.id}`, card);
    },

    deleteCard(cardId) {
        return axios.delete(`/card/${cardId}`);
    }
    
}