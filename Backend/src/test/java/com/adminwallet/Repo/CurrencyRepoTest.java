package com.adminwallet.Repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CurrencyRepoTest {
    @Autowired
    private CurrencyRepo repoUnderTest;
}