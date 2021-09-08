import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { SharedModule } from './../shared/shared.module';

import { FlexLayoutModule } from "@angular/flex-layout";
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';
import {MatPaginatorModule} from '@angular/material/paginator';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgxPaginationModule } from 'ngx-pagination' ;

import { AdminRoutingModule } from './admin-routing.module';

import { ListAnnonceComponent } from './annonce/list-annonce/list-annonce.component';
import { CreateAnnonceComponent } from './annonce/create-annonce/create-annonce.component';
import { ListChauffeurComponent } from './chauffeur/list-chauffeur/list-chauffeur.component';
import { CreateChauffeurComponent } from './chauffeur/create-chauffeur/create-chauffeur.component';
import { ListPermisComponent } from './permis/list-permis/list-permis.component';
import { CreatePermisComponent } from './permis/create-permis/create-permis.component';
import { ListRecruteurComponent } from './recruteur/list-recruteur/list-recruteur.component';
import { CreateRecruteurComponent } from './recruteur/create-recruteur/create-recruteur.component';

import { CreateLocalityComponent } from './locality/create-locality/create-locality.component';
import { ListLocalityComponent } from './locality/list-locality/list-locality.component';
import { ListNotationComponent } from './list-notation/list-notation.component';
import { ListUtilisateurComponent } from './utilisateur/list-utilisateur/list-utilisateur.component';
import { CreateUtilisateurComponent } from './utilisateur/create-utilisateur/create-utilisateur.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ViewChauffeurComponent } from './chauffeur/view-chauffeur/view-chauffeur.component';
import { CreateTarifComponent } from './tarif/create-tarif/create-tarif.component';
import { ListTarifComponent } from './tarif/list-tarif/list-tarif.component';
import { ViewRecruteurComponent } from './recruteur/view-recruteur/view-recruteur.component';
import { AnnonceByStatusEncoursComponent } from './annonce/annonce-by-status-encours/annonce-by-status-encours.component';


@NgModule({
  declarations: [
    CreateRecruteurComponent,
    ListRecruteurComponent,
    CreatePermisComponent,
    ListPermisComponent,
    CreateChauffeurComponent,
    ListChauffeurComponent,
    CreateAnnonceComponent,
    ListAnnonceComponent,
    CreateLocalityComponent,
    ListLocalityComponent,
    ListNotationComponent,
    ListUtilisateurComponent,
    CreateUtilisateurComponent,
    DashboardComponent,
    ViewChauffeurComponent,
    CreateTarifComponent,
    ListTarifComponent,
    ViewRecruteurComponent,
    AnnonceByStatusEncoursComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MatDialogModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatButtonModule,
    MatSnackBarModule,
    MatCardModule,
    MatExpansionModule,

    MatPaginatorModule,
    Ng2SearchPipeModule,
    NgxPaginationModule,
  ]
})
export class AdminModule { }
