package com.techelevator.dao;

import com.techelevator.model.Card;

import java.security.Principal;

public interface CardDao {
   void createCard(Principal principal, Card newCard);
}
