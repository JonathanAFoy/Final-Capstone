package com.techelevator.dao;

import com.techelevator.model.Deck;
import java.security.Principal;
import java.util.List;

public interface DeckDao {
    List<Deck> getDecks(Principal principal);

    void createDeck(Principal principal, Deck newDeck);

    void addCard(int deckId, int cardId);

    void removeCardFromDeck(int deckId, int cardId, Principal principal);

    void deleteDeck(int deckId, Principal principal);

}
