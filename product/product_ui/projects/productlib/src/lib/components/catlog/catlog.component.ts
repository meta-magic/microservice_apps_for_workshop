import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {select, Store} from '@ngrx/store';
import {ProductNamespace} from '../../store/state';
import {AddToCart, UpdateProductData} from '../../store/action';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpService} from '../../services/http.service';
import {SERVICE_URL} from '../../constant/service.constant';
import {CommonService} from '../../services/common.service';
import {Product} from "../../models/product.model";
import {CookieService} from "ngx-cookie-service";

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
              private _httpService: HttpService,
              public _cService: CommonService,
              private _cookieService: CookieService,
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
      this._httpService.restCall(SERVICE_URL.GET_ALL_PRODUCT, 'get').toPromise()
        .then((res: any) => {
          this._cService.showLoader = false;
          this.store.dispatch(new UpdateProductData(res.data));
        });
  }

  addToCartHandle(product: Product) {
    const requestObject = {};
          requestObject['userId'] = this._cookieService.get('tokenId');
          requestObject['productId'] = product.id;
          requestObject['price'] = product.price;
          requestObject['qty'] = 1;

    this._httpService.restCall(SERVICE_URL.ADD_TO_CART, 'post', requestObject).toPromise()
        .then((res: any) => {
          this._cService.showLoader = false;
          this.store.dispatch(new AddToCart(res.data));
        });
  }

  productDetailsHandle(product: Product) {
    this._router.navigate(['../catlog', product.id], {relativeTo: this.route});
  }


}
