import {ModuleWithProviders, NgModule} from '@angular/core';
import { OrdersComponent } from './components/orders.component';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';
import { AmexioWidgetModule } from 'amexio-ng-extensions';
import {SharedlibModule} from "sharedlib";


const routes: Routes = [
  {
    path: '',
    redirectTo: 'all',
    pathMatch: 'full'
  },{
    path: 'all',
    component: OrdersComponent
  },
];

@NgModule({
  declarations: [OrdersComponent],
  imports: [
    CommonModule,SharedlibModule.forRoot(),
    RouterModule.forChild(routes), AmexioWidgetModule
  ],
})
export class OrderslibModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: OrderslibModule,
      providers: []
    };
  }
}
