import { Component, OnInit } from '@angular/core';
import { ContactService } from '../contact.service';
import { Country } from '../country';

@Component({
  selector: 'filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  countries : Country[]=[];
  selectedCountry = "all";
  selectedState = "all";
  
  constructor( private contactService : ContactService) { }

  ngOnInit() {
    this.contactService.getAllCountries().subscribe(
    response => { this.countries = response as Country[] }, error => { alert("error in get customers" + error) });  
  }

}
