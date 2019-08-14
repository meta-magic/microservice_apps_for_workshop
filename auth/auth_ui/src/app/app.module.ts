
import {CommonModule} from "@angular/common";

import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {AmexioWidgetModule} from 'amexio-ng-extensions';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SharedlibModule} from "sharedlib";
import {RouterModule, Routes} from "@angular/router";
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
    CommonModule,
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
