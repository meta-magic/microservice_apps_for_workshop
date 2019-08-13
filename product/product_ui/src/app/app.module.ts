import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
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
    RouterModule.forRoot(routes, { useHash : true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
