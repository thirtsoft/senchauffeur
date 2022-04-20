import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
//import { HttpClientModule } from '@angular/common/http';

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
import { MatPaginatorModule } from '@angular/material/paginator';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgxPaginationModule } from 'ngx-pagination' ;
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { SharedModule } from '..//shared/shared.module';

import { CustomerRoutingModule } from './customer-routing.module';

import { EditorModule } from '@tinymce/tinymce-angular';

import { CreateNotificationComponent } from './notation/create-notification/create-notification.component';
import { ListNotificationComponent } from './notation/list-notification/list-notification.component';
import { ContactComponent } from './entreprise/contact/contact.component';

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
import { SearchJobByReferenceComponent } from './job/search-job-by-reference/search-job-by-reference.component';
import { ListJobPermisComponent } from './job/list-job-permis/list-job-permis.component';
import { PublierOffreComponent } from './job/publier-offre/publier-offre.component';
import { ListTarifCustomerComponent } from './job/list-tarif-customer/list-tarif-customer.component';
import { TermeConditionsComponent } from './entreprise/terme-conditions/terme-conditions.component';
import { CreateRatingComponent } from './rating/create-rating/create-rating.component';
import { ListRatingComponent } from './rating/list-rating/list-rating.component';
import { AddNewsleterComponent } from './add-newsleter/add-newsleter.component';
import { SuccessContactComponent } from './entreprise/success-contact/success-contact.component';


@NgModule({
  declarations: [
    DetailChauffeurComponent,
    SearchChauffeurComponent,
    CreateNotificationComponent,
    ListNotificationComponent,
    ContactComponent,
    JobbrowserComponent,
    JobDetailComponent,
    CandidatebrowerComponent,
    ListLocalityComponent,
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
    SearchJobByReferenceComponent,
    ListJobPermisComponent,
    PublierOffreComponent,
    ListTarifCustomerComponent,
    TermeConditionsComponent,
    CreateRatingComponent,
    ListRatingComponent,
    AddNewsleterComponent,
    SuccessContactComponent,
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

    MatPaginatorModule,
    Ng2SearchPipeModule,
    NgxPaginationModule,
    EditorModule,
    NgbModule,
  ],
  entryComponents: [
    ListJobComponent, CreateJobComponent, PublierOffreComponent,
    JobbrowserComponent,
    
  ]
})
export class CustomerModule { }
