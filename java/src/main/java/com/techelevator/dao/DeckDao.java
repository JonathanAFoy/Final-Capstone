package com.techelevator.dao;

import com.techelevator.model.Deck;
import java.security.Principal;

public interface DeckDao {

    void createDeck(Principal principal, Deck newDeck);
}