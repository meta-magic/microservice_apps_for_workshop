import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { Routes, RouterModule } from '@angular/router';
import {HttpClientModule} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {AmexioWidgetModule} from "amexio-ng-extensions";
import {StoreModule} from "@ngrx/store";


const routes: Routes = [
  {
    path:'', redirectTo: 'payment', pathMatch:'full'
  },
  {
    path : 'payment', loadChildren: './modules/payment.module#PaymentModule'
  }
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, AmexioWidgetModule,
    StoreModule.forRoot({}),
    RouterModule.forRoot(routes, { useHash : true})
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
