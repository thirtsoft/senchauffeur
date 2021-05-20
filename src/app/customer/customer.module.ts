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

import { SharedModule } from '..//shared/shared.module';

import { CustomerRoutingModule } from './customer-routing.module';
import { DetailChauffeurComponent } from './detail-chauffeur/detail-chauffeur.component';
import { SearchChauffeurComponent } from './search-chauffeur/search-chauffeur.component';
import { SearchOffreEmployeComponent } from './search-offre-employe/search-offre-employe.component';
import { CreateNotificationComponent } from './notation/create-notification/create-notification.component';
import { ListNotificationComponent } from './notation/list-notification/list-notification.component';
import { ContactComponent } from './contact/contact.component';
import { JobbrowserComponent } from './jobbrowser/jobbrowser.component';
import { JobDetailComponent } from './job-detail/job-detail.component';
import { CandidatebrowerComponent } from './candidatebrower/candidatebrower.component';
import { AideComponent } from './aide/aide.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AboutUsComponent } from './about-us/about-us.component';


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
    AideComponent,
    PageNotFoundComponent,
    AboutUsComponent
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

  ]
})
export class CustomerModule { }
