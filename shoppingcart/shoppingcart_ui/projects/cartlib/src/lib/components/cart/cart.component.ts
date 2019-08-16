import { Component, OnInit } from '@angular/core';
import {CartResponse} from '../../models/cart.model';
import {ActivatedRoute, Router} from '@angular/router';
import {SERVICE_URL} from "../../constant/service.constant";
import {select, Store} from '@ngrx/store';
import {CartNamespace} from "../../store/state";
import {AddToShoopingCart} from "../../store/action";
import {SharedService} from "sharedlib";


@Component({
  selector: 'lib-cartlib',
  templateUrl: 'cart.component.html'
})
export class CartComponent implements OnInit {
  cartInfo: CartResponse;
  hasRecordFound = true;
  constructor(  private _sharedService: SharedService,
                private _router: Router,
                private _store: Store<CartNamespace.ICart>,
                private route: ActivatedRoute) {
    this.cartInfo = new CartResponse();
      this._store.pipe(select(CartNamespace.getState)).subscribe((cartState: any) => {
          if (cartState) {
              this.cartInfo = <CartResponse>cartState.cartData;
            this.hasRecordFound = this.cartInfo.shoppingCart.length > 0 ? true : false;

          }

      });
  }

  ngOnInit() {
    this.getCartData();
  }

  getCartData() {
    this._sharedService._httpService.restCall(SERVICE_URL.GET_CART_PRODUCT, 'get').toPromise()
        .then((res: any) => {
          this._sharedService._commonService.showLoader = false;
            this._store.dispatch(new AddToShoopingCart(res.data));
        });
  }

  payClickHandle(event: any) {
    this._router.navigate(['../../payment'], {relativeTo: this.route});

  }

}
