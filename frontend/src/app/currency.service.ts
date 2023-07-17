import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Currency } from './currency';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class CurrencyService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getCurrencies(): Observable<Currency[]> {
    return this.http.get<Currency[]>(`${this.apiServerUrl}/currency/all`);
  }

  public addCurrency(currency: Currency): Observable<Currency> {
    return this.http.post<Currency>(`${this.apiServerUrl}/currency/add`, currency);
  }

  public updateCurrency(currency: Currency): Observable<Currency> {
    return this.http.put<Currency>(`${this.apiServerUrl}/currency/update`, currency);
  }

  public deleteCurrency(currencyId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/currency/delete/${currencyId}`);
  }
}
