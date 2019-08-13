import { NgModule } from '@angular/core';
import { CartComponent } from './components/cart/cart.component';
import {AmexioWidgetModule} from 'amexio-ng-extensions';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {PaymentComponent} from './components/payment/payment.component';
import {FormsModule} from "@angular/forms";

export const route = [{
  path: '', component: CartComponent
},
  {
    path: 'payment', component: PaymentComponent
  }
];

@NgModule({
  declarations: [CartComponent, PaymentComponent],
  imports: [
    CommonModule, HttpClientModule, FormsModule,
    AmexioWidgetModule, RouterModule.forChild(route)
  ],
})
export class CartLibModule { }
