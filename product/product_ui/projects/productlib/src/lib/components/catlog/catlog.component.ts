import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {select, Store} from '@ngrx/store';
import {ProductNamespace} from '../../store/state';
import {AddToCart, UpdateProductData} from '../../store/action';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-catlog',
  templateUrl: './catlog.component.html'
})
export class CatlogComponent implements OnInit {

  productList: any[] = [];

  constructor(
              private _router: Router,
              private route: ActivatedRoute,
              private _httpClient: HttpClient,
              private store: Store<ProductNamespace.IProduct>) {
    this.store.pipe(select(ProductNamespace.getState)).subscribe((productState: any) => {
      if (productState) {
        this.productList = productState.productData;
      }

    });
  }

  ngOnInit() {
    this.getProduct();
  }

  getProduct() {
    this._httpClient.get('assets/data/product.json').subscribe((res: any) => {
      debugger;
      this.store.dispatch(new UpdateProductData(res.response));
    });
  }

  addToCartHandle(product: any) {
    this.store.dispatch(new AddToCart(product));

  }

  moreHandle(product: any) {
   this._router.navigate(['../more'], {relativeTo: this.route});
  }


}
