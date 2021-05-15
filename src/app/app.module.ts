import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { AddEmployeComponent } from './customer/emploi/add-employe/add-employe.component';
import { ListEmployeComponent } from './customer/emploi/list-employe/list-employe.component';
import { HomeComponent } from './customer/home/home.component';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { ProfilComponent } from './auth/profil/profil.component';
import { SuccessRegisterComponent } from './auth/success-register/success-register.component';
import { ConfirmRegisterComponent } from './auth/confirm-register/confirm-register.component';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { BackendSidebarComponent } from './shared/backend-sidebar/backend-sidebar.component';
import { DefaultLayoutComponent } from './layouts/default-layout/default-layout.component';
import { BackendHeaderComponent } from './shared/backend-header/backend-header.component';
import { BackendFooterComponent } from './shared/backend-footer/backend-footer.component';
import { FooterComponent } from './shared/footer/footer.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';
import { HeaderComponent } from './shared/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    AddEmployeComponent,
    ListEmployeComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    ProfilComponent,
    SuccessRegisterComponent,
    ConfirmRegisterComponent,
    AdminLayoutComponent,
    BackendSidebarComponent,
    DefaultLayoutComponent,
    BackendHeaderComponent,
    BackendFooterComponent,
    FooterComponent,
    SidebarComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
