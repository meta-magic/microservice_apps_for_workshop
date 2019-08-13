import {ModuleWithProviders, NgModule} from '@angular/core';
import { OrderslibComponent } from './orderslib.component';
import {RouterModule, Routes} from '@angular/router';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import { AmexioWidgetModule } from 'amexio-ng-extensions';


const routes: Routes = [
  {
    path: '',
    redirectTo: 'all',
    pathMatch: 'full'
  },{
    path: 'all',
    component: OrderslibComponent
  },
];

@NgModule({
  declarations: [OrderslibComponent],
  imports: [
    CommonModule, HttpClientModule,
    RouterModule.forChild(routes), AmexioWidgetModule
  ],
  exports: [OrderslibComponent]
})
export class OrderslibModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: OrderslibModule,
      providers: []
    };
  }
}
