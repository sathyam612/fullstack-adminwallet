package com.adminwallet.Controllers;
import com.adminwallet.Entity.Currency;
import com.adminwallet.Services.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyControllers {
    public CurrencyService currencyService;
    public CurrencyControllers(CurrencyService currencyService){
        this.currencyService=currencyService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Currency>> getAllCurrency(){
        List<Currency>currencies=currencyService.findAllCurrency();
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Currency>> getCurrencyById(@PathVariable("id") Long id){
        Optional<Currency>currency=currencyService.findCurrencyById(id);
        return new ResponseEntity<>(currency, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Currency>addCurrency(@RequestBody Currency currency){
        Currency newCurrency=currencyService.addCurrency(currency);
        return new ResponseEntity<>(newCurrency, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Currency>updateEmployee(@RequestBody Currency currency){
        Currency updateCurrency=currencyService.updateCurrency(currency);
        return new ResponseEntity<>(updateCurrency, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteCurrency(@PathVariable("id") Long id) {
        currencyService.deleteCurrencyById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
