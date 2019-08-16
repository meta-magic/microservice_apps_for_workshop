import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {SharedlibModule, AuthCanLoadService, CatlogResolverService, AuthCanActiveService} from "sharedlib";
const routes: Routes = [
  {
    path : '', loadChildren: './modules/auth.module#AuthModule'
  },
  {
    path : 'home', canActivate: [AuthCanActiveService], component: HomeComponent,
    children: [
      {
        path: '', redirectTo: 'product', pathMatch: 'full'
      },
      {
        path : 'product',
        canLoad: [AuthCanLoadService],
    /*    resolve: {
          product: CatlogResolverService
        },*/
        loadChildren: './modules/product.module#ProductModule'
      },
      {
        path : 'order', canLoad: [AuthCanLoadService], loadChildren: './modules/orders.module#OrdersModule'
      },
      {
        path : 'cart', canLoad: [AuthCanLoadService], loadChildren: './modules/cart.module#CartModule'
      },
      {
        path : 'payment', canLoad: [AuthCanLoadService], loadChildren: './modules/payment.module#PaymentModule'
      }
    ]
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash : true}),
    SharedlibModule.forRoot()],
  exports: [RouterModule]
})
export class AppRoutingModule { }
