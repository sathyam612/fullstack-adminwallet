package com.adminwallet.Services;

import com.adminwallet.Entity.Currency;
import com.adminwallet.Repo.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CurrencyService {
    private final CurrencyRepo currencyRepo;
    @Autowired
    public CurrencyService (CurrencyRepo currencyRepo){
        this.currencyRepo=currencyRepo;
    }
    public Currency addCurrency(Currency currency){
        return currencyRepo.save(currency);
    }
    public List<Currency> findAllCurrency(){
        return currencyRepo.findAll();
    }
    public Currency updateCurrency(Currency currency){
        return currencyRepo.save(currency);
    }
    public Optional<Currency> findCurrencyById(Long id){
        return currencyRepo.findById(id);
    }
    public  void deleteCurrencyById(Long id){
        currencyRepo.deleteById(id);
    }
}
