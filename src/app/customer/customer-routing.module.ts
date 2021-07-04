import { CompaniesComponent } from './companies/companies.component';
import { FaqComponent } from './faq/faq.component';
import { SubmitResumeComponent } from './submit-resume/submit-resume.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListNotificationComponent } from './notation/list-notification/list-notification.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { AideComponent } from './aide/aide.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { JobDetailComponent } from './job/job-detail/job-detail.component';
import { JobbrowserComponent } from './job/jobbrowser/jobbrowser.component';
import { ContactComponent } from './contact/contact.component';
import { CreateNotificationComponent } from './notation/create-notification/create-notification.component';
import { SearchOffreEmployeComponent } from './search-offre-employe/search-offre-employe.component';
import { SearchChauffeurComponent } from './candidate/search-chauffeur/search-chauffeur.component';
import { DetailChauffeurComponent } from './candidate/detail-chauffeur/detail-chauffeur.component';
import { AddEmployeComponent } from './emploi/add-employe/add-employe.component';
import { ListEmployeComponent } from './emploi/list-employe/list-employe.component';
import { HomeComponent } from './home/home.component';
import { CandidatebrowerComponent } from './candidate/candidatebrower/candidatebrower.component';
import { ListJobComponent } from './job/list-job/list-job.component';
import { CreateJobComponent } from './job/create-job/create-job.component';


const routes: Routes = [
  { path: '',   redirectTo: 'home', pathMatch: 'full' },

  {
    path: '',
    component: HomeComponent
  },

  {
    path: 'emploies',
    component: ListEmployeComponent
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
    path: 'newEmploi',
    component: AddEmployeComponent
  },
  {
    path: 'detail-chauffeur',
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
    path: 'jobs',
    component: ListJobComponent
  },
  {
    path: 'createJob',
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
    path: 'job-detail/:reference',
    component: JobDetailComponent
  },
  {
    path: 'companies',
    component: CompaniesComponent
  },
  {
    path: 'search-offre',
    component: SearchOffreEmployeComponent
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
    path: 'faq',
    component: FaqComponent
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
