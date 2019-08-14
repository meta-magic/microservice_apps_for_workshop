import {ModuleWithProviders, NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { AmexioWidgetModule } from 'amexio-ng-extensions';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {AddProductComponent} from "./components/add-product/add-product.component";
import {CatlogComponent} from "./components/catlog/catlog.component";
import {StoreModule} from "@ngrx/store";
import {ProductReducer} from "./store/reducer";
import { ProductDetailsComponent} from "./components/product-details/product-details.component";
import {FormsModule} from "@angular/forms";
import {SharedlibModule} from "sharedlib";



const routes: Routes = [
  {
    path: '',
    redirectTo: 'catlog',
    pathMatch: 'full'
  },
  {
    path: 'catlog',
    component: CatlogComponent
  },
  {
    path: 'add-product',
    component: AddProductComponent
  },
  {
    path: 'catlog/:id',
    component: ProductDetailsComponent
  }
];

@NgModule({
  declarations: [CatlogComponent, AddProductComponent, ProductDetailsComponent],
  imports: [
    CommonModule, HttpClientModule, FormsModule,SharedlibModule.forRoot(),
    StoreModule.forFeature('productState', ProductReducer),
    RouterModule.forChild(routes), AmexioWidgetModule
  ],
  exports: [CatlogComponent, AddProductComponent]
})
export class ProductlibModule {

  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ProductlibModule,
      providers: []
    };
  }
}
