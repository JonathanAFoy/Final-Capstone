package com.techelevator.dao;

import com.techelevator.model.Card;
import java.security.Principal;
import java.util.List;

public interface CardDao {
   List<Card> getCards(Principal principal);

   List<Card> getDeckCards(Principal principal, int deckId);

   Card createCard(Principal principal, Card newCard);

   void deleteCard(int cardId, Principal principal);

}
