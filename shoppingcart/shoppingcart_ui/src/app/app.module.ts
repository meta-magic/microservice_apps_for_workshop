import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HttpClientModule} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {AmexioWidgetModule} from "amexio-ng-extensions";
import { AppComponent } from './app.component';
import { StoreModule } from '@ngrx/store';

const routes: Routes = [
  {
    path:'', redirectTo: 'cart', pathMatch:'full'
  },
  {
    path : 'cart', loadChildren: './modules/cart.module#CartModule'
  }
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AmexioWidgetModule,
    StoreModule.forRoot({}),
    RouterModule.forRoot(routes, { useHash : true})
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
