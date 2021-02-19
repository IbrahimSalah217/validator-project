import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod'

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private httpClient:HttpClient) { }

  getAllCustomers() {
    let url = environment.url + "customers";
    return this.httpClient.get(url);
  }

  getAllCountries() {
    let url = environment.url + "countries";
    return this.httpClient.get(url);
  }

  getCustomers(country: String, state: String){
    let url = environment.url + `customers/${country}/${state}`;
    return this.httpClient.get(url);
  }
}
