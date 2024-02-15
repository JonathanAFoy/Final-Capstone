package com.techelevator.dao;

import com.techelevator.model.Deck;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDeckDao implements DeckDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcDeckDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Deck> getDecks(Principal principal) {
        List<Deck> decks = new ArrayList<>();
        String sql = "SELECT * FROM deck WHERE username = ?;";
        String username = principal.getName();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while(results.next()) {
                decks.add(mapRowToDeck(results));
            }
        } catch (DataAccessException dae) {
            String detailedMessage = "Data access exception during: " + dae.getMessage();
            System.out.println(detailedMessage);
        }
        return decks;
    }

    @Override
    public void createDeck(Principal principal, Deck newDeck){
        String sql = "INSERT INTO deck (username, deck_title, deck_tags, is_public) VALUES (?,?,?,?) RETURNING deck_id";
        String username = principal.getName();
        try {
            int newDeckId = jdbcTemplate.queryForObject(sql, Integer.class, username, newDeck.getDeckTitle(), newDeck.getDeckTags(), false);
            newDeck.setDeckId(newDeckId);
        } catch (DataAccessException dae) {
            String detailedMessage = "Data access exception during: " + dae.getMessage();
            System.out.println(detailedMessage);
        }
    }

    @Override
    public void deleteDeck(int deckId) {
        String sql = "DELETE FROM deck_card WHERE deck_id = ?; " +
                "DELETE FROM deck WHERE deck_id = ?;";
        try {
            jdbcTemplate.update(sql, deckId, deckId);
        } catch (DataAccessException dae) {
            String detailedMessage = "Data access exception during: " + dae.getMessage();
            System.out.println(detailedMessage);
        }
    }

    @Override
    public void addCard(int deckId, int cardId) {
        String sql = "INSERT INTO deck_card (deck_id, card_id) VALUES (?,?);";
        try {
            jdbcTemplate.update(sql, deckId, cardId);
        } catch (DataAccessException dae) {
            String detailedMessage = "Data access exception during: " +dae.getMessage();
            System.out.println(detailedMessage);
        }
    }

    private Deck mapRowToDeck(SqlRowSet rowSet) {
        Deck deck = new Deck();
        deck.setDeckId(rowSet.getInt("deck_id"));
        deck.setUsername(rowSet.getString("username"));
        deck.setDeckTitle(rowSet.getString("deck_title"));
        deck.setDeckTags(rowSet.getString("deck_tags"));
        deck.setPublic(rowSet.getBoolean("is_public"));
        return deck;
    }

}
