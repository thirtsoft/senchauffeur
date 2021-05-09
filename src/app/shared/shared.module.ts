import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedRoutingModule } from './shared-routing.module';


@NgModule({
  declarations: [
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    BackendHeaderComponent,
    BackendSidebarComponent,
    BackendFooterComponent,
    MatedialogComponent,
  ],
   exports: [
    HeaderComponent,
    SidebarComponent,
    FooterComponent,
    BackendHeaderComponent,
    BackendSidebarComponent,
    BackendFooterComponent,
    MatedialogComponent,
  ],
  imports: [
    CommonModule,
    SharedRoutingModule
  ]
})
export class SharedModule { }
