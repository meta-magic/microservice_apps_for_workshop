import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {HomeComponent} from "./components/home/home.component";
import {AddUserComponent} from "./components/adduser/adduser.component"
const routes: Routes = [

  {
    path: '', redirectTo: 'login', pathMatch: 'full'
  },
  {
    path : 'login', component: LoginComponent
  },
  {
    path : 'add-user', component: AddUserComponent
  },
  {
    path : 'home', component: HomeComponent,
    children: [
      {
        path: '', redirectTo: 'product', pathMatch: 'full'
      },
      {
        path : 'product', loadChildren: './modules/product.module#ProductModule'
      },
      {
        path : 'order', loadChildren: './modules/orders.module#OrdersModule'
      },
      {
        path : 'cart', loadChildren: './modules/cart.module#CartModule'
      }
    ]
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash : true}) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
