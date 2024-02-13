package com.techelevator.dao;

import com.techelevator.model.Card;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.security.Principal;

@Component
public class JdbcCardDao implements CardDao {
    private final JdbcTemplate jdbcTemplate;
    public JdbcCardDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createCard(Principal principal, Card newCard) {
        String sql = "INSERT INTO card (username, front_text, back_text, card_tags, is_public) VALUES (?,?,?,?,?) RETURNING card_id";
        String username = principal.getName();
        try{
            int newCardId = jdbcTemplate.queryForObject(sql, Integer.class, username, newCard.getFrontText(), newCard.getBackText(), newCard.getCardTags(), false);
            newCard.setCardId(newCardId);
        }catch (DataAccessException dae) {
            String detailedMessage = "Data access exception during: " + dae.getMessage();
            System.out.println(detailedMessage);
        }
    }
}
