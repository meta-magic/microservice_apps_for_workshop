import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StoreModule } from '@ngrx/store';
import { AmexioWidgetModule } from 'amexio-ng-extensions';
import { CookieService } from 'ngx-cookie-service';


const routes: Routes = [
  {
    path:'', redirectTo: 'product', pathMatch:'full'
  },
  {
    path : 'product', loadChildren: './modules/product.module#ProductModule'
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
    StoreModule.forRoot({}),
    AmexioWidgetModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes, { useHash : true})
  ],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
