package com.techelevator.dao;

import com.techelevator.model.Deck;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class JdbcDeckDao implements DeckDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcDeckDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createDeck(Principal principal, Deck newDeck){
        String sql = "INSERT INTO deck (username, deck_title, deck_tags, is_public) VALUES (?,?,?,?) RETURNING deck_id";
        String username = principal.getName();
        try{
            int newDeckId = jdbcTemplate.queryForObject(sql, Integer.class, username, newDeck.getDeckTitle(), newDeck.getDeckTags(), false);
            newDeck.setDeckId(newDeckId);
        }catch (DataAccessException dae) {
            String detailedMessage = "Data access exception during: " + dae.getMessage();
            System.out.println(detailedMessage);
        }
    }

}