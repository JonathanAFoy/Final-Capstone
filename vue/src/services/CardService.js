import axios from 'axios';

export default {

    // CARD

    getCard(cardId) {
        return axios.get(`/cards/${cardId}`);
    },

    createCard(card) {
        return axios.post(`/add-card`, card);
    },

    updateCard(card) {
        return axios.put(`/cards/${card.id}`, card);
    },

    deleteCard(cardId) {
        return axios.delete(`/cards.${cardId}`);
    }
    
}