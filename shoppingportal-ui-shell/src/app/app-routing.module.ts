import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
const routes: Routes = [
  {
    path : '', loadChildren: './modules/auth.module#AuthModule'
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
      },
      {
        path : 'payment', loadChildren: './modules/payment.module#PaymentModule'
      }
    ]
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash : true}) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
