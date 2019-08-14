/**
 * Created by dattaram on 12/8/19.
 */
import {Component, OnInit} from '@angular/core';
import { AmexioCreditCardModel } from 'amexio-ng-extensions';
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from '@ngrx/store';
import {CartNamespace} from "../../store/state";
import {CartResponse} from "../../models/cart.model";
import {SERVICE_URL} from "../../constant/service.constant";
import {SharedService} from "sharedlib";

@Component({
  selector: 'payment',
  templateUrl: 'payment.component.html'
})

export class PaymentComponent implements OnInit {
  paymentInfo: AmexioCreditCardModel;
  cartInfo: CartResponse;
  constructor(private _router: Router,
              private _sharedService: SharedService,
              private _store: Store<CartNamespace.ICart>,
              private route: ActivatedRoute) {
    this.paymentInfo = new AmexioCreditCardModel();
    this.cartInfo = new CartResponse();


    this._store.pipe(select(CartNamespace.getState)).subscribe((cartState: any) => {
      if (cartState) {
        this.cartInfo = <CartResponse>cartState.cartData;
      }

    });
  }


  ngOnInit() {
  }

  payHandle() {
    try {
      const requestBody = {
        "shoppintCart": this.createRequestBody()
      };
      this._sharedService._httpService.restCall(SERVICE_URL.PAYMENT, 'post', requestBody).toPromise()
          .then((res: any) => {
            this._sharedService._commonService.showLoader = false;
            this._router.navigate(['../../order'], {relativeTo: this.route});
          });
    } catch (error) {
    }
  }

  createRequestBody(): any[] {
    const cartCollection = Object.assign([], this.cartInfo.shoppingCart);
    cartCollection.forEach((item: any) => {
      delete item.id;
      delete item.productDetails;
      delete item.userId;
      delete item.isSelected;
    });
    return cartCollection;
  }
}
