package com.techelevator.dao;

import com.techelevator.model.Card;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCardDao implements CardDao {
    private final JdbcTemplate jdbcTemplate;
    public JdbcCardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Card> getCards(Principal principal) {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM card WHERE username = ?;";
        String username = principal.getName();
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            while(results.next()) {
                cards.add(mapRowToCard(results));
            }
        } catch (DataAccessException dae) {
            String detailedMessage = "Data access exception during: " + dae.getMessage();
            System.out.println(detailedMessage);
        }
        return cards;
    }

    @Override
    public void createCard(Principal principal, Card newCard) {
        String sql = "INSERT INTO card (username, front_text, back_text, card_tags, is_public) VALUES (?,?,?,?,?) RETURNING card_id";
        String username = principal.getName();
        try {
            int newCardId = jdbcTemplate.queryForObject(sql, Integer.class, username, newCard.getFrontText(), newCard.getBackText(), newCard.getCardTags(), false);
            newCard.setCardId(newCardId);
        } catch (DataAccessException dae) {
            String detailedMessage = "Data access exception during: " + dae.getMessage();
            System.out.println(detailedMessage);
        }
    }

    private Card mapRowToCard(SqlRowSet rowSet) {
        Card card = new Card();
        card.setCardId(rowSet.getInt("card_id"));
        card.setUsername(rowSet.getString("username"));
        card.setFrontText(rowSet.getString("front_text"));
        card.setBackText(rowSet.getString("back_text"));
        card.setCardTags(rowSet.getString("card_tags"));
        card.setPublic(rowSet.getBoolean("is_public"));
        return card;
    }

}
