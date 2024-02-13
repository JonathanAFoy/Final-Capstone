package com.techelevator.dao;

import com.techelevator.model.Deck;
import java.security.Principal;
import java.util.List;

public interface DeckDao {

    void createDeck(Principal principal, Deck newDeck);

    List<Deck> getDecks(Principal principal);
}