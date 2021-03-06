import { TermeConditionsComponent } from './entreprise/terme-conditions/terme-conditions.component';
import { ListTarifCustomerComponent } from './job/list-tarif-customer/list-tarif-customer.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListNotificationComponent } from './notation/list-notification/list-notification.component';
import { AboutUsComponent } from './entreprise/about-us/about-us.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { JobDetailComponent } from './job/job-detail/job-detail.component';
import { JobbrowserComponent } from './job/jobbrowser/jobbrowser.component';
import { ContactComponent } from './entreprise/contact/contact.component';
import { CreateNotificationComponent } from './notation/create-notification/create-notification.component';
import { SearchChauffeurComponent } from './candidate/search-chauffeur/search-chauffeur.component';
import { DetailChauffeurComponent } from './candidate/detail-chauffeur/detail-chauffeur.component';
import { HomeComponent } from './home/home.component';
import { CandidatebrowerComponent } from './candidate/candidatebrower/candidatebrower.component';
import { ListJobComponent } from './job/list-job/list-job.component';
import { CreateJobComponent } from './job/create-job/create-job.component';
import { PublierOffreComponent } from './job/publier-offre/publier-offre.component';
import { CompaniesComponent } from './entreprise/companies/companies.component';
import { FaqComponent } from './entreprise/faq/faq.component';
import { SubmitResumeComponent } from './submit-resume/submit-resume.component';
import { SuccessContactComponent } from './entreprise/success-contact/success-contact.component';

const routes: Routes = [
  { path: '',   redirectTo: 'home', pathMatch: 'full' },

  {
    path: '',
    component: HomeComponent
  },

  { path: 'search/:keyword',
    component: HomeComponent
  },
 
  {
    path: 'browse-candidates',
    component: CandidatebrowerComponent
  },
  {
    path: 'submit-resume',
    component: SubmitResumeComponent
  },
  {
    path: 'detail-chauffeur/:id',
    component: DetailChauffeurComponent
  },
  {
    path: 'search-chauffeur',
    component: SearchChauffeurComponent
  },
  {
    path: 'browse-job',
    component: JobbrowserComponent
  },
  {
    path: 'jobPermis/:id',
    component: JobbrowserComponent
  },
  {
    path: 'jobPermis',
    component: JobbrowserComponent
  },
  {
    path: 'searchInJob/:libelle',
    component: JobbrowserComponent
  },
 
  {
    path: 'jobs/:id',
    component: ListJobComponent
  },
   {
    path: 'jobs/:id',
    component: CreateJobComponent
  },
  {
    path: 'jobs',
    component: CreateJobComponent
  },
  {
    path: 'createJob/:id',
    component: CreateJobComponent
  },
 
  {
    path: 'browse-candidate',
    component: CandidatebrowerComponent
  },
  {
    path: 'locality/:id',
    component: CandidatebrowerComponent
  },
  {
    path: 'locality',
    component: CandidatebrowerComponent
  },
  {
    path: 'searchInCandidates/:disponibility',
    component: CandidatebrowerComponent
  },

  {
    path: 'job-detail/:reference',
    component: JobDetailComponent
  },
  {
    path: 'companies',
    component: CompaniesComponent
  },
  {
    path: 'publier-offre',
    component: PublierOffreComponent
  },
  {
    path: 'wishes',
    component: ListNotificationComponent
  },
  {
    path: 'newWishe',
    component: CreateNotificationComponent
  },
  {
    path: 'about-us',
    component: AboutUsComponent
  },
  {
    path: 'contact-us',
    component: ContactComponent
  },
  {
    path: 'success-contact',
    component: SuccessContactComponent
  },
  {
    path: 'faq',
    component: FaqComponent
  },
  {
    path: 'browse-tarifs',
    component: ListTarifCustomerComponent
  },
  {
    path: 'termesCondition',
    component: TermeConditionsComponent
  },
  {
    path: '404',
    component: PageNotFoundComponent
  },



];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
