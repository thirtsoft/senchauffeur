import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { DetailChauffeurComponent } from './detail-chauffeur/detail-chauffeur.component';
import { SearchChauffeurComponent } from './search-chauffeur/search-chauffeur.component';
import { SearchOffreEmployeComponent } from './search-offre-employe/search-offre-employe.component';


@NgModule({
  declarations: [DetailChauffeurComponent, SearchChauffeurComponent, SearchOffreEmployeComponent],
  imports: [
    CommonModule,
    CustomerRoutingModule
  ]
})
export class CustomerModule { }
