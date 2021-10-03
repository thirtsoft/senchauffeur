import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

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
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { SharedModule } from '..//shared/shared.module';

import { CustomerRoutingModule } from './customer-routing.module';
import { DataTablesModule } from 'angular-datatables';

import { PdfViewerModule } from 'ng2-pdf-viewer';
import { EditorModule } from '@tinymce/tinymce-angular';

import { SearchOffreEmployeComponent } from './job/search-offre-employe/search-offre-employe.component';
import { CreateNotificationComponent } from './notation/create-notification/create-notification.component';
import { ListNotificationComponent } from './notation/list-notification/list-notification.component';
import { ContactComponent } from './entreprise/contact/contact.component';

import { AideComponent } from './aide/aide.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AboutUsComponent } from './entreprise/about-us/about-us.component';
import { CompaniesComponent } from './entreprise/companies/companies.component';
import { FaqComponent } from './entreprise/faq/faq.component';
import { SubmitResumeComponent } from './submit-resume/submit-resume.component';
import { DetailChauffeurComponent } from './candidate/detail-chauffeur/detail-chauffeur.component';
import { SearchChauffeurComponent } from './candidate/search-chauffeur/search-chauffeur.component';
import { JobbrowserComponent } from './job/jobbrowser/jobbrowser.component';
import { JobDetailComponent } from './job/job-detail/job-detail.component';
import { CandidatebrowerComponent } from './candidate/candidatebrower/candidatebrower.component';
import { ListLocalityComponent } from './candidate/list-locality/list-locality.component';
import { CreateJobComponent } from './job/create-job/create-job.component';
import { ListJobComponent } from './job/list-job/list-job.component';
import { RecentJobComponent } from './job/recent-job/recent-job.component';
import { HomeComponent } from './home/home.component';
import { JobersComponent } from './job/jobers/jobers.component';
import { SearchInCandidatesComponent } from './candidate/search-in-candidates/search-in-candidates.component';
import { ListJobParmisComponent } from './job/list-job-parmis/list-job-parmis.component';
import { SearchJobByReferenceComponent } from './job/search-job-by-reference/search-job-by-reference.component';
import { ListJobPermisComponent } from './job/list-job-permis/list-job-permis.component';
import { PublierOffreComponent } from './job/publier-offre/publier-offre.component';
import { ListTarifCustomerComponent } from './job/list-tarif-customer/list-tarif-customer.component';
import { TermeConditionsComponent } from './entreprise/terme-conditions/terme-conditions.component';
import { DetailEmployeurComponent } from './employeur/detail-employeur/detail-employeur.component';
import { JetonEmployeurComponent } from './employeur/jeton-employeur/jeton-employeur.component';
import { InfoEmployeurComponent } from './employeur/info-employeur/info-employeur.component';
import { ModifyInfoEmployeurComponent } from './employeur/modify-info-employeur/modify-info-employeur.component';
import { ModifyPasswordEmployeurComponent } from './employeur/modify-password-employeur/modify-password-employeur.component';
import { CreateRatingComponent } from './rating/create-rating/create-rating.component';
import { ListRatingComponent } from './rating/list-rating/list-rating.component';




@NgModule({
  declarations: [
    DetailChauffeurComponent,
    SearchChauffeurComponent,
    SearchOffreEmployeComponent,
    CreateNotificationComponent,
    ListNotificationComponent,
    ContactComponent,
    JobbrowserComponent,
    JobDetailComponent,
    CandidatebrowerComponent,
    ListLocalityComponent,
    AideComponent,
    PageNotFoundComponent,
    AboutUsComponent,
    CompaniesComponent,
    FaqComponent,
    SubmitResumeComponent,
    CreateJobComponent,
    ListJobComponent,
    RecentJobComponent,
    HomeComponent,
    JobersComponent,
    SearchInCandidatesComponent,
    ListJobParmisComponent,
    SearchJobByReferenceComponent,
    ListJobPermisComponent,
    PublierOffreComponent,
    ListTarifCustomerComponent,
    TermeConditionsComponent,
    DetailEmployeurComponent,
    JetonEmployeurComponent,
    InfoEmployeurComponent,
    ModifyInfoEmployeurComponent,
    ModifyPasswordEmployeurComponent,
    CreateRatingComponent,
    ListRatingComponent,
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    SharedModule,
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
    DataTablesModule,

    MatPaginatorModule,
    Ng2SearchPipeModule,
    NgxPaginationModule,
    PdfViewerModule,
    EditorModule,
    NgbModule,

  ]
})
export class CustomerModule { }
