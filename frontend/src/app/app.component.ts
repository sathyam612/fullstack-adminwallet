import { Component, OnInit } from '@angular/core';
import { Currency } from './currency';
import { CurrencyService } from './currency.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public currencies: Currency[];
  public editCurrency: Currency;
  public deleteCurrency: Currency;

  constructor(private currencyService: CurrencyService){}

  ngOnInit() {
    this.getCurrencies();
  }

  public getCurrencies(): void {
    this.currencyService.getCurrencies().subscribe(
      (response: Currency[]) => {
        this.currencies = response;
        console.log(this.currencies);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddCurrency(addForm: NgForm): void {
    document.getElementById('add-currency-form').click();
    this.currencyService.addCurrency(addForm.value).subscribe(
      (response: Currency) => {
        console.log(response);
        this.getCurrencies();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateCurrency(currency: Currency): void {
    this.currencyService.updateCurrency(currency).subscribe(
      (response: Currency) => {
        console.log(response);
        this.getCurrencies();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteCurrency(currencyId: number): void {
    this.currencyService.deleteCurrency(currencyId).subscribe(
      (response: void) => {
        console.log(response);
        this.getCurrencies();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public searchCurrency(key: string): void {
    console.log(key);
    const results: Currency[] = [];
    for (const currency of this.currencies) {
      if (currency.name.toLowerCase().indexOf(key.toLowerCase()) !== -1)
    {
        results.push(currency);
      }
    }
    this.currencies = results;
    if (results.length === 0 || !key) {
      this.getCurrencies();
    }
  }

  public onOpenModal(currency: Currency, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addCurrencyModal');
    }
    if (mode === 'edit') {
      this.editCurrency = currency;
      button.setAttribute('data-target', '#updateCurrencyModal');
    }
    if (mode === 'delete') {
      this.deleteCurrency= currency;
      button.setAttribute('data-target', '#deleteCurrencyModal');
    }
    container.appendChild(button);
    button.click();
  }



}
