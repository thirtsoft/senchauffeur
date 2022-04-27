import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DEFAULT_ROUTES } from './routes/default-layout-routes';

import { DefaultLayoutComponent } from './layouts/default-layout/default-layout.component';


const routes: Routes = [
  
  {
    path: '',
    component: DefaultLayoutComponent,
    children: DEFAULT_ROUTES
  },
 
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  },
  { path: '**', redirectTo: '404'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
