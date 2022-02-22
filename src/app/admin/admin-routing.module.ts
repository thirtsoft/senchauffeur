import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AccueilbackendComponent } from './accueilbackend/accueilbackend.component';
import { SignUpComponent } from './authentication/sign-up/sign-up.component';
import { SignInComponent } from './authentication/sign-in/sign-in.component';
import { ListHistoriqueLoginComponent } from './historique/list-historique-login/list-historique-login.component';
import { ListHistoriqueAnnonceComponent } from './historique/list-historique-annonce/list-historique-annonce.component';
import { SendEmailToChauffeurComponent } from './email/send-email-to-chauffeur/send-email-to-chauffeur.component';
import { SendEmailToEmployeurComponent } from './email/send-email-to-employeur/send-email-to-employeur.component';
import { SendEmailToNewsleterComponent } from './email/send-email-to-newsleter/send-email-to-newsleter.component';
import { ListEmailComponent } from './email/list-email/list-email.component';
import { ListNewsleterComponent } from './email/list-newsleter/list-newsleter.component';
import { CreateJetonComponent } from './jeton/create-jeton/create-jeton.component';
import { ListJetonComponent } from './jeton/list-jeton/list-jeton.component';
import { CreateUtilisateurComponent } from './utilisateur/create-utilisateur/create-utilisateur.component';
import { CreateLocalityComponent } from './locality/create-locality/create-locality.component';
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

import { ViewRecruteurComponent } from './recruteur/view-recruteur/view-recruteur.component';
import { CreateTarifComponent } from './tarif/create-tarif/create-tarif.component';
import { ListTarifComponent } from './tarif/list-tarif/list-tarif.component';
import { ViewChauffeurComponent } from './chauffeur/view-chauffeur/view-chauffeur.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AnnonceByStatusEncoursComponent } from './annonce/annonce-by-status-encours/annonce-by-status-encours.component';


const routes: Routes = [

  { path: '',   redirectTo: 'signIn', pathMatch: 'full' },
  {
    path: 'signIn',
    component: SignInComponent
  },

  { path: 'accueil', component: AccueilbackendComponent,
    children : [
      { path: '',   redirectTo: 'dashboard', pathMatch: 'full' },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'signUp',
        component: SignUpComponent
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
        path: 'viewrecruteur/:id',
        component: ViewRecruteurComponent
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
        path: 'annonceEncours',
        component: AnnonceByStatusEncoursComponent
      },
      {
        path: 'tarifs',
        component: ListTarifComponent
      },
      {
        path: 'tarif',
        component: CreateTarifComponent
      },
      {
        path: 'tarif/:id',
        component: CreateTarifComponent
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
        path: 'jetons',
        component: ListJetonComponent
      },
      {
        path: 'jeton/:id',
        component: CreateJetonComponent
      },
      {
        path: 'jeton',
        component: CreateJetonComponent
      },
      {
        path: 'notifications',
        component: ListNotationComponent
      },
      {
        path: 'newsleters',
        component: ListNewsleterComponent
      },
      {
        path: 'sendEmailToNewsleter',
        component: SendEmailToNewsleterComponent
      },
      {
        path: 'emails',
        component: ListEmailComponent
      },
      {
        path: 'sendEmailToEmployeur',
        component: SendEmailToEmployeurComponent
      },
      {
        path: 'sendEmailToChauffeur',
        component: SendEmailToChauffeurComponent
      },
      {
        path: 'historiqueAnnonces',
        component: ListHistoriqueAnnonceComponent
      },
      {
        path: 'historiqueLogins',
        component: ListHistoriqueLoginComponent
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
    
    ]
  },
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
