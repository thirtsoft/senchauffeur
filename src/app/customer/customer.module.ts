import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { CustomerRoutingModule } from './customer-routing.module';
import { DetailChauffeurComponent } from './detail-chauffeur/detail-chauffeur.component';
import { SearchChauffeurComponent } from './search-chauffeur/search-chauffeur.component';
import { SearchOffreEmployeComponent } from './search-offre-employe/search-offre-employe.component';
import { CreateNotificationComponent } from './notation/create-notification/create-notification.component';
import { ListNotificationComponent } from './notation/list-notification/list-notification.component';


@NgModule({
  declarations: [
    DetailChauffeurComponent,
    SearchChauffeurComponent,
    SearchOffreEmployeComponent,
    CreateNotificationComponent,
    ListNotificationComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,

  ]
})
export class CustomerModule { }
