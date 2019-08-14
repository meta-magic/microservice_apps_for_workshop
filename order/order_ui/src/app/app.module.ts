import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { Routes, RouterModule } from '@angular/router';
import {HttpClientModule} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {AmexioWidgetModule} from "amexio-ng-extensions";


const routes: Routes = [
  {
    path:'', redirectTo: 'order', pathMatch:'full'
  },
  {
    path : 'order', loadChildren: './modules/orders.module#OrdersModule'
  }
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, AmexioWidgetModule,
    RouterModule.forRoot(routes, { useHash : true})
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
