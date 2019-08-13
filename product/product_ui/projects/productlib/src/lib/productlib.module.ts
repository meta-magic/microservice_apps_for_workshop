import {ModuleWithProviders, NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { AmexioWidgetModule } from 'amexio-ng-extensions';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {AddProductComponent} from "./components/add-product/add-product.component";
import {CatlogComponent} from "./components/catlog/catlog.component";
import {StoreModule} from "@ngrx/store";
import {ProductReducer} from "./store/reducer";
import {MoreComponent} from "./components/more/more.component";
import {FormsModule} from "@angular/forms";



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
    path: 'more',
    component: MoreComponent
  }
];

@NgModule({
  declarations: [CatlogComponent, AddProductComponent, MoreComponent],
  imports: [
    CommonModule, HttpClientModule, FormsModule,
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
