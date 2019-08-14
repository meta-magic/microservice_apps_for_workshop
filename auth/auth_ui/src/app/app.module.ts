

import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {AmexioWidgetModule} from 'amexio-ng-extensions';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SharedlibModule} from "sharedlib";
import {RouterModule, Routes} from "@angular/router";
import {BrowserModule} from "@angular/platform-browser";
const routes: Routes = [
  {
    path : '', loadChildren: './modules/auth.module#AuthModule'
  }
  ];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AmexioWidgetModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    SharedlibModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
