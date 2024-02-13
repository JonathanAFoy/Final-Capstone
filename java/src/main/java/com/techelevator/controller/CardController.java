package com.techelevator.controller;

import com.techelevator.dao.JdbcCardDao;
import com.techelevator.dao.JdbcUserDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Card;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;

@RestController
public class CardController {
    final private JdbcCardDao cardDao;
    final private UserDao userDao;

    public CardController(JdbcCardDao cardDao, JdbcUserDao userDao) {
        this.cardDao = cardDao;
        this.userDao = userDao;
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/card/create")
    public void createCard(@Valid @RequestBody Card newCard, Principal principal) {
        cardDao.createCard(principal, newCard);
    }
}