package com.techelevator.controller;

import com.techelevator.dao.JdbcDeckDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Deck;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;

@RestController
public class DeckController {
    final private JdbcDeckDao deckDao;
    final private UserDao userDao;

    public DeckController(JdbcDeckDao deckDao, JdbcUserDao userDao) {
        this.deckDao = deckDao;
        this.userDao = userDao;
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/deck/create")
    public void createDeck(@Valid @RequestBody Deck newDeck, Principal principal) {
        deckDao.createDeck(principal, newDeck);
    }
}
