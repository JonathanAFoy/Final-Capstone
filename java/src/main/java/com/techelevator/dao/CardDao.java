package com.techelevator.dao;

import com.techelevator.model.Card;
import java.security.Principal;
import java.util.List;

public interface CardDao {
   List<Card> getCards(String username);

   List<Card> getDeckCards(int deckId, String username);

   Card createCard(Card newCard, String username);

   void deleteCard(int cardId, String username);

}
