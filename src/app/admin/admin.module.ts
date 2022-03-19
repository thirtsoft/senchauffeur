import { UpdateUsernameInfo } from './../auth/security/profile-info';
import { httpInterceptorProviders } from './../auth/security/auth-interceptor';
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { SharedModule } from './../shared/shared.module';

import { FlexLayoutModule } from "@angular/flex-layout";
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
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
import { EditorModule } from '@tinymce/tinymce-angular';

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
import { ListHistoriqueAnnonceComponent } from './historique/list-historique-annonce/list-historique-annonce.component';
import { ListHistoriqueLoginComponent } from './historique/list-historique-login/list-historique-login.component';
import { CreateJetonComponent } from './jeton/create-jeton/create-jeton.component';
import { ListJetonComponent } from './jeton/list-jeton/list-jeton.component';
import { ListEmailComponent } from './email/list-email/list-email.component';
import { ListNewsleterComponent } from './email/list-newsleter/list-newsleter.component';
import { SendEmailToEmployeurComponent } from './email/send-email-to-employeur/send-email-to-employeur.component';
import { SendEmailToChauffeurComponent } from './email/send-email-to-chauffeur/send-email-to-chauffeur.component';
import { SendEmailToNewsleterComponent } from './email/send-email-to-newsleter/send-email-to-newsleter.component';
import { SignInComponent } from './authentication/sign-in/sign-in.component';
import { SignUpComponent } from './authentication/sign-up/sign-up.component';
import { SuccessRegisterComponent } from './authentication/success-register/success-register.component';
import { ProfilComponent } from './authentication/profil/profil.component';
import { AccueilComponent } from './accueil/accueil.component';
import { AccueilbackendComponent } from './accueilbackend/accueilbackend.component';
import { ListValidatedAnnonceComponent } from './annonce/list-validated-annonce/list-validated-annonce.component';
import { ListRejectedAnnonceComponent } from './annonce/list-rejected-annonce/list-rejected-annonce.component';
import { ListPendingAnnonceComponent } from './annonce/list-pending-annonce/list-pending-annonce.component';
import { UpdateStatusOfAnnonceComponent } from './annonce/update-status-of-annonce/update-status-of-annonce.component';
import { ListNumberOfAnnonceByMonthComponent } from './chart/list-number-of-annonce-by-month/list-number-of-annonce-by-month.component';
import { ListNumberOfAnnonceByYearComponent } from './chart/list-number-of-annonce-by-year/list-number-of-annonce-by-year.component';
import { ListNumberOfChauffeurByYearComponent } from './chart/list-number-of-chauffeur-by-year/list-number-of-chauffeur-by-year.component';
import { ListNumberOfChauffeurByMonthComponent } from './chart/list-number-of-chauffeur-by-month/list-number-of-chauffeur-by-month.component';
import { ViewAnnonceComponent } from './annonce/view-annonce/view-annonce.component';
import { UpdatePhotoChauffeurComponent } from './chauffeur/update-photo-chauffeur/update-photo-chauffeur.component';
import { UpdateCvChauffeurComponent } from './chauffeur/update-cv-chauffeur/update-cv-chauffeur.component';
import { ChartComponent } from './chart/chart.component';
import { UpdatePasswordComponent } from './authentication/update-password/update-password.component';
import { UpdateProfilComponent } from './authentication/update-profil/update-profil.component';
import { UpdateUsernameComponent } from './authentication/update-username/update-username.component';


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
    AnnonceByStatusEncoursComponent,
    ListHistoriqueAnnonceComponent,
    ListHistoriqueLoginComponent,
    CreateJetonComponent,
    ListJetonComponent,
    ListEmailComponent,
    ListNewsleterComponent,
    SendEmailToEmployeurComponent,
    SendEmailToChauffeurComponent,
    SendEmailToNewsleterComponent,
    SignInComponent,
    SignUpComponent,
    SuccessRegisterComponent,
    ProfilComponent,
    AccueilComponent,
    AccueilbackendComponent,
    ListValidatedAnnonceComponent,
    ListRejectedAnnonceComponent,
    ListPendingAnnonceComponent,
    UpdateStatusOfAnnonceComponent,
    ListNumberOfAnnonceByMonthComponent,
    ListNumberOfAnnonceByYearComponent,
    ListNumberOfChauffeurByYearComponent,
    ListNumberOfChauffeurByMonthComponent,
    ViewAnnonceComponent,
    UpdatePhotoChauffeurComponent,
    UpdateCvChauffeurComponent,
    ChartComponent,
    UpdatePasswordComponent,
    UpdateProfilComponent,
    UpdateUsernameComponent
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
    EditorModule,
  ],

  providers: [
    DatePipe, { provide: MAT_DIALOG_DATA, useValue: {} ,},
    {provide: MatDialogRef, useValue: {} },
    httpInterceptorProviders
  ],

  entryComponents: [
    CreateAnnonceComponent, UpdateStatusOfAnnonceComponent,
    UpdateUsernameInfo, UpdatePhotoChauffeurComponent,
    DashboardComponent, UpdateCvChauffeurComponent,
    ProfilComponent, UpdateProfilComponent, UpdateUsernameComponent,
    UpdatePasswordComponent,
    
  ]
})
export class AdminModule { }
