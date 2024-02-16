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
import java.util.List;

@RestController
@CrossOrigin
public class DeckController {
    final private JdbcDeckDao deckDao;
    final private UserDao userDao;

    public DeckController(JdbcDeckDao deckDao, JdbcUserDao userDao) {
        this.deckDao = deckDao;
        this.userDao = userDao;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/deck")
    public List<Deck> getDecks(Principal principal) {
        return deckDao.getDecks(principal);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/deck/create")
    public void createDeck(@Valid @RequestBody Deck newDeck, Principal principal) {
        deckDao.createDeck(principal, newDeck);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/deck/{deckId}/{cardId}")
    public void addCard(@PathVariable int deckId, @PathVariable int cardId) {
        deckDao.addCard(deckId, cardId);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/deck/{deckId}")
    public void updateDeck(@PathVariable int deckId, @Valid @RequestBody Deck newDeck, Principal principal){
        deckDao.updateDeck(deckId, newDeck, principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/deck/{deckId}/{cardId}")
    public void removeCardFromDeck(@PathVariable int deckId, @PathVariable int cardId, Principal principal) {
        deckDao.removeCardFromDeck(deckId, cardId, principal);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/deck/{deckId}")
    public void deleteDeck(@PathVariable int deckId, Principal principal) {
        deckDao.deleteDeck(deckId, principal);
    }

}
