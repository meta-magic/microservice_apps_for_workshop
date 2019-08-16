import { Component, OnInit } from '@angular/core';
import {select, Store} from '@ngrx/store';
import {ProductNamespace} from '../../store/state';
import {AddToCart, UpdateProductData} from '../../store/action';
import {ActivatedRoute, Router} from '@angular/router';
import {SERVICE_URL} from '../../constant/service.constant';
import {Product} from "../../models/product.model";
import {SharedService} from "sharedlib";

@Component({
  selector: 'app-catlog',
  templateUrl: './catlog.component.html'
})
export class CatlogComponent implements OnInit {

  productList: any[] = [];

  constructor(
              private _sharedService: SharedService,
              private _router: Router,
              private route: ActivatedRoute,
              private store: Store<ProductNamespace.IProduct>) {

    this.store.pipe(select(ProductNamespace.getState)).subscribe((productState: any) => {
      if (productState) {
          this._sharedService._commonService.showLoader = false;
          this.productList = productState.productData;
      }

    });
  }

  ngOnInit() {
    this.getProduct();
  /*    this.route.data
          .subscribe((product: any) => {
          debugger;
          this._sharedService._commonService.showLoader = false;
              this.store.dispatch(new UpdateProductData(product.product.data));
          });*/

  }

  getProduct() {
      this._sharedService._httpService.restCall(SERVICE_URL.GET_ALL_PRODUCT, 'get').toPromise()
        .then((res: any) => {
          this.store.dispatch(new UpdateProductData(res.data));
        });
  }

  addToCartHandle(product: Product) {
    const requestObject = {};
          requestObject['userId'] = this._sharedService._cookieService.get('tokenId');
          requestObject['productId'] = product.id;
          requestObject['price'] = product.price;
          requestObject['qty'] = 1;

    this._sharedService._httpService.restCall(SERVICE_URL.ADD_TO_CART, 'post', requestObject).toPromise()
        .then((res: any) => {
          this._sharedService._commonService.showLoader = false;
          this._sharedService._commonService.setInfoMsgCollection(res.message);
          this.getCartData();
        });
  }


    getCartData() {
        this._sharedService._httpService.restCall(SERVICE_URL.GET_CART_PRODUCT, 'get').toPromise()
            .then((res: any) => {
                this.store.dispatch(new AddToCart(res.data.shoppingCart));
            });
    }

  productDetailsHandle(product: Product) {
    this._router.navigate(['../catlog', product.id], {relativeTo: this.route});
  }


}
