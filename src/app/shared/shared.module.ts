import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { SharedRoutingModule } from './shared-routing.module';

import { ToastrModule } from 'ngx-toastr';
import { MatDialogModule, MatDialogRef, } from '@angular/material/dialog';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';

import { BackendFooterComponent } from './backend-footer/backend-footer.component';
import { BackendSidebarComponent } from './backend-sidebar/backend-sidebar.component';
import { BackendHeaderComponent } from './backend-header/backend-header.component';
import { FooterComponent } from './footer/footer.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HeaderComponent } from './header/header.component';
import { MatdialogComponent } from './matdialog/matdialog.component';

@NgModule({
  declarations: [
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    BackendHeaderComponent,
    BackendSidebarComponent,
    BackendFooterComponent,
    MatdialogComponent,

  ],
   exports: [
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    BackendHeaderComponent,
    BackendSidebarComponent,
    BackendFooterComponent,

  ],
  imports: [
    CommonModule,
    SharedRoutingModule,
    MatInputModule,
    MatFormFieldModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatButtonModule,
    MatSnackBarModule,
    MatCardModule,
    MatExpansionModule,
    ToastrModule.forRoot(),
    MatDialogModule,
    MatDialogRef,
    MatIconModule,
  ],
  providers: [
    DatePipe,
    {
      provide: MAT_DIALOG_DATA,
      useValue: {} ,
    },
    {
      provide: MatDialogRef,
      useValue: {}
    },

  ],
})
export class SharedModule { }

