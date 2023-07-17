package com.adminwallet.Repo;
import com.adminwallet.Entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepo extends JpaRepository<Currency,Long> {

}
