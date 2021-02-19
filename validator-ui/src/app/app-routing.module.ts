import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PhoneListComponent } from "./validator-app/phone-list/phone-list.component";

const routes: Routes = [
  {path: '', component: PhoneListComponent},
  {path: 'customers/:selectedCountry/:selectedState', component: PhoneListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
