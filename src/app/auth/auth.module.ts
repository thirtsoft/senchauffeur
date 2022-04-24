import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { ReactiveFormsModule  } from '@angular/forms';
import { FormsModule }   from '@angular/forms';

import { LoginComponent } from './login/login.component';
import { SuccessRegisterComponent } from './success-register/success-register.component';
import { ProfilComponent } from './profil/profil.component';
import { RegisterComponent } from './register/register.component';



@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    SuccessRegisterComponent,
    ProfilComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AuthModule { }
