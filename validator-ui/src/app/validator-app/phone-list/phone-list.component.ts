import { Component, OnInit } from '@angular/core';
import { ContactService } from '../contact.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../customer';

@Component({
  selector: 'app-phone-list',
  templateUrl: './phone-list.component.html',
  styleUrls: ['./phone-list.component.css']
})
export class PhoneListComponent implements OnInit {

  customers : Customer[]=[];
  selectedCountry : String;
  selectedState : String;

  displayedColumns: string[] = ['name', 'phone', 'countryName', 'countryCode', 'state']
  constructor(private contactService:ContactService,private route: ActivatedRoute,private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(parobj=>{this.selectedCountry = parobj.get('selectedCountry'); 
    this.selectedState = parobj.get('selectedState');
    if(this.selectedState && this.selectedCountry){
      this.contactService.getCustomers(this.selectedCountry, this.selectedState).subscribe(
        response => { this.customers = response as Customer[] }, error => { alert("error in get customers" + error) });
    } else {
      this.contactService.getAllCustomers().subscribe(
        response => { this.customers = response as Customer[] }, error => { alert("error in get customers" + error) });
    }
  });
      
  }
}
