import { ListAnnonceComponent } from './annonce/list-annonce/list-annonce.component';
import { CreateAnnonceComponent } from './annonce/create-annonce/create-annonce.component';
import { ListChauffeurComponent } from './chauffeur/list-chauffeur/list-chauffeur.component';
import { CreateChauffeurComponent } from './chauffeur/create-chauffeur/create-chauffeur.component';
import { ListPermisComponent } from './permis/list-permis/list-permis.component';
import { CreatePermisComponent } from './permis/create-permis/create-permis.component';
import { ListRecruteurComponent } from './recruteur/list-recruteur/list-recruteur.component';
import { CreateRecruteurComponent } from './recruteur/create-recruteur/create-recruteur.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

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


import { AdminRoutingModule } from './admin-routing.module';

import { CreateLocalityComponent } from './locality/create-locality/create-locality.component';
import { ListLocalityComponent } from './locality/list-locality/list-locality.component';
import { ListNotationComponent } from './list-notation/list-notation.component';


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
    ListNotationComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ]
})
export class AdminModule { }
