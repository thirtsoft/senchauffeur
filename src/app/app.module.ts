import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { SharedModule } from './shared/shared.module';

import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { DefaultLayoutComponent } from './layouts/default-layout/default-layout.component';
import { AccueilComponent } from './layouts/accueil/accueil.component';
import { LocalityComponent } from './candidate/locality/locality.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    AppComponent,

    AdminLayoutComponent,
    DefaultLayoutComponent,
    AccueilComponent,
    LocalityComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    NgbModule,



  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
