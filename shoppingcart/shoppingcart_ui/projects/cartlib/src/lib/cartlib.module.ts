import { NgModule } from '@angular/core';
import { CartComponent } from './components/cart/cart.component';
import {AmexioWidgetModule} from 'amexio-ng-extensions';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {PaymentComponent} from './components/payment/payment.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {StoreModule} from "@ngrx/store";
import {CartReducer} from "./store/reducer";
import {SharedlibModule} from "sharedlib";


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
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedlibModule.forRoot(),
    StoreModule.forFeature('cartState', CartReducer),
    AmexioWidgetModule, RouterModule.forChild(route)
  ],
})
export class CartLibModule { }
