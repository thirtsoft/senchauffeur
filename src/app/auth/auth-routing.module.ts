import { LoginComponent } from './login/login.component';
import { ConfirmRegisterComponent } from './confirm-register/confirm-register.component';
import { SuccessRegisterComponent } from './success-register/success-register.component';
import { ProfilComponent } from './profil/profil.component';
import { RegisterComponent } from './register/register.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [

  {
    path: 'signup',
    component: RegisterComponent
  },
  {
    path: 'profil',
    component: ProfilComponent
  },
  {
    path: 'success-register',
    component: SuccessRegisterComponent
  },
  {
    path: 'confirm-register',
    component: ConfirmRegisterComponent
  },
  {
    path: 'signin',
    component: LoginComponent
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
