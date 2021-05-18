import { CreateUtilisateurComponent } from './utilisateur/create-utilisateur/create-utilisateur.component';
import { ListUtilisateurComponent } from './utilisateur/list-utilisateur/list-utilisateur.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListNotationComponent } from './list-notation/list-notation.component';
import { ListLocalityComponent } from './locality/list-locality/list-locality.component';
import { CreateAnnonceComponent } from './annonce/create-annonce/create-annonce.component';
import { ListAnnonceComponent } from './annonce/list-annonce/list-annonce.component';
import { CreateRecruteurComponent } from './recruteur/create-recruteur/create-recruteur.component';
import { ListRecruteurComponent } from './recruteur/list-recruteur/list-recruteur.component';
import { CreatePermisComponent } from './permis/create-permis/create-permis.component';
import { ListPermisComponent } from './permis/list-permis/list-permis.component';
import { CreateChauffeurComponent } from './chauffeur/create-chauffeur/create-chauffeur.component';
import { ListChauffeurComponent } from './chauffeur/list-chauffeur/list-chauffeur.component';


const routes: Routes = [
  {
    path: 'chauffeurs',
    component: ListChauffeurComponent
  },
  {
    path: 'newChauffeur',
    component: CreateChauffeurComponent
  },
  {
    path: 'permis',
    component: ListPermisComponent
  },
  {
    path: 'newPermis',
    component: CreatePermisComponent
  },
  {
    path: 'recruteurs',
    component: ListRecruteurComponent
  },
  {
    path: 'newRecruteur',
    component: CreateRecruteurComponent
  },
  {
    path: 'annonces',
    component: ListAnnonceComponent
  },
  {
    path: 'newAnnonce',
    component: CreateAnnonceComponent
  },
  {
    path: 'localites',
    component: ListLocalityComponent
  },
  {
    path: 'notifications',
    component: ListNotationComponent
  },
  {
    path: 'utilisateurs',
    component: ListUtilisateurComponent
  },
  {
    path: 'newUtilisateur',
    component: CreateUtilisateurComponent
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
