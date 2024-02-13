package com.techelevator.dao;

import com.techelevator.model.Card;
import java.security.Principal;
import java.util.List;

public interface CardDao {
   List<Card> getCards(Principal principal);
   void createCard(Principal principal, Card newCard);

}
