package com.adminwallet.Services;

import com.adminwallet.Entity.Currency;
import com.adminwallet.Repo.CurrencyRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {
    @Mock
    private CurrencyRepo currencyRepo;
    private CurrencyService underTest;

    @BeforeEach
    void setUp() {
        underTest=new CurrencyService(currencyRepo);
    }

    @Test
    void testAddCurrency() {
        Currency currency=new Currency(2L,"ethereum",3400D,"0x64ff637fb478863b7468bc97d30a5bf3a428a1fd");
        underTest.addCurrency(currency);
        ArgumentCaptor<Currency>currencyArgumentCaptor=ArgumentCaptor.forClass(Currency.class);
        verify(currencyRepo).save(currencyArgumentCaptor.capture());
        Currency capturedValue=currencyArgumentCaptor.getValue();
        assertThat(capturedValue).isEqualTo(currency);
    }

    @Test
    void testFindAllCurrency() {
        underTest.findAllCurrency();
        verify(currencyRepo).findAll();
    }

    @Test
    void testUpdateCurrency() {
        Currency updatedCurrency=new Currency(2L,"ethereum",3400D,"0x64ff637fb478863b7468bc97d30a5bf3a428a1fd");
        underTest.updateCurrency(updatedCurrency);
        ArgumentCaptor<Currency>currencyArgumentCaptor=ArgumentCaptor.forClass(Currency.class);
        verify(currencyRepo).save(currencyArgumentCaptor.capture());
        Currency capturedValue=currencyArgumentCaptor.getValue();
        assertThat(capturedValue).isEqualTo(updatedCurrency);
    }

    @Test
    void findCurrencyById() {
        underTest.findCurrencyById(2L);
        verify(currencyRepo).findById(2L);
    }

    @Test
    void testDeleteCurrencyById() {
        underTest.deleteCurrencyById(2L);
        verify(currencyRepo).deleteById(2L);
    }
}