import { NgModule } from '@angular/core';
import {LoginComponent} from "./components/login/login.component";
import {AmexioWidgetModule} from 'amexio-ng-extensions';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AddUserComponent} from "./components/adduser/adduser.component"
import {SharedlibModule} from "sharedlib";
import {CommonModule} from "@angular/common";
import {RouterModule, Routes} from "@angular/router";

const routes: Routes = [
  {
    path : '', redirectTo: 'login', pathMatch: 'full'
  },
  {
    path : 'login', component: LoginComponent
  },
  {
    path : 'add-user', component: AddUserComponent
  }
  ];

@NgModule({
  declarations: [
    LoginComponent,
    AddUserComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    AmexioWidgetModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    SharedlibModule.forRoot()
  ],
  exports: []
})
export class AuthLibModule { }
