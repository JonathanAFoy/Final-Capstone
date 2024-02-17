import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      currDeck: {},
      currCards: []
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },

      SET_DECK(state, deck) {
        state.currDeck = deck;
      },

      SET_CARD_LIST(state, cardList) {
        state.currCards = cardList;
      },

      ADD_CARD_CURRDECK(state, card) {
        state.currCards.push(card);
      },
      UPDATE_CARD(state, cardInfo) {
        const card = state.currCards.find( crd => crd.cardId == cardInfo.cardId);
        if (card) {
          card.flipped = cardInfo.flipped;
          card.correct = cardInfo.correct;
        }
      },
      SET_ALL_CARDS_TO_FRONT(state) {
        state.currCards.forEach( card => card.flipped = false);
      }
    },
  });
  return store;
}
