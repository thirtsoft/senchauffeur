import { ViewChauffeurComponent } from './chauffeur/view-chauffeur/view-chauffeur.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CreateLocalityComponent } from './locality/create-locality/create-locality.component';
import { CreateUtilisateurComponent } from './utilisateur/create-utilisateur/create-utilisateur.component';
import { ListUtilisateurComponent } from './utilisateur/list-utilisateur/list-utilisateur.component';
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
    path: '',
    redirectTo: 'dashborad',
    pathMatch: 'full'
  },

  {
    path: 'dashborad',
    component: DashboardComponent
  },
  {
    path: 'chauffeurs',
    component: ListChauffeurComponent
  },
  {
    path: 'chauffeur',
    component: CreateChauffeurComponent
  },
  {
    path: 'chauffeur/:id',
    component: CreateChauffeurComponent
  },
  {
    path: 'viewchauffeur/:id',
    component: ViewChauffeurComponent
  },
  {
    path: 'listPermis',
    component: ListPermisComponent
  },
  {
    path: 'permis',
    component: CreatePermisComponent
  },
  {
    path: 'permis/:id',
    component: CreatePermisComponent
  },
  {
    path: 'recruteurs',
    component: ListRecruteurComponent
  },
  {
    path: 'recruteur',
    component: CreateRecruteurComponent
  },
  {
    path: 'recruteur/:id',
    component: CreateRecruteurComponent
  },
  {
    path: 'annonces',
    component: ListAnnonceComponent
  },
  {
    path: 'annonce',
    component: CreateAnnonceComponent
  },
  {
    path: 'annonce/:id',
    component: CreateAnnonceComponent
  },
  {
    path: 'localites',
    component: ListLocalityComponent
  },
  {
    path: 'localite',
    component: CreateLocalityComponent
  },
  {
    path: 'localite/:id',
    component: CreateLocalityComponent
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
    path: 'utilisateur',
    component: CreateUtilisateurComponent
  },
  {
    path: 'utilisateur/:id',
    component: CreateUtilisateurComponent
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
