package com.techelevator.dao;

import com.techelevator.model.Deck;
import java.security.Principal;
import java.util.List;

public interface DeckDao {
    List<Deck> getDecks(String username);

    Deck getDeck(int deckId, String username);

    void createDeck(Deck newDeck, String username);

    void addCard(int deckId, int cardId);

    void updateDeck(int deckId, Deck newDeck, String username);

    void removeCardFromDeck(int deckId, int cardId, String username);

    void deleteDeck(int deckId, String username);

}
