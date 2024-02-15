package com.techelevator.dao;

import com.techelevator.model.Deck;
import java.security.Principal;
import java.util.List;

public interface DeckDao {
    List<Deck> getDecks(Principal principal);

    void createDeck(Principal principal, Deck newDeck);

    void deleteDeck(int deckId);

    void addCard(int deckId, int cardId);

}
