import { CreateNotificationComponent } from './notation/create-notification/create-notification.component';
import { ListNotationComponent } from './../admin/list-notation/list-notation.component';
import { SearchOffreEmployeComponent } from './search-offre-employe/search-offre-employe.component';
import { SearchChauffeurComponent } from './search-chauffeur/search-chauffeur.component';
import { DetailChauffeurComponent } from './detail-chauffeur/detail-chauffeur.component';
import { AddEmployeComponent } from './emploi/add-employe/add-employe.component';
import { ListEmployeComponent } from './emploi/list-employe/list-employe.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'emploies',
    component: ListEmployeComponent
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
    path: 'search-offre',
    component: SearchOffreEmployeComponent
  },
  {
    path: 'wishes',
    component: ListNotationComponent
  },
  {
    path: 'newWishe',
    component: CreateNotificationComponent
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
