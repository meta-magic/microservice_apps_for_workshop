/**
 * Created by dattaram on 12/8/19.
 */
import {Component, OnInit} from '@angular/core';
import { AmexioCreditCardModel } from 'amexio-ng-extensions';
import {ActivatedRoute, Router} from "@angular/router";
import {select, Store} from '@ngrx/store';
import {CartNamespace} from "../../store/state";
import {CartResponse} from "../../models/cart.model";
import {HttpService} from "../../services/http.service";
import {SERVICE_URL} from "../../constant/service.constant";
import {CommonService} from "../../services/common.service";


@Component({
  selector: 'payment',
  templateUrl: 'payment.component.html'
})

export class PaymentComponent implements OnInit {
  paymentInfo: AmexioCreditCardModel;
  cartInfo: CartResponse;
  constructor(private _router: Router,
              private _httpService: HttpService,
              public _cService: CommonService,
              private _store: Store<CartNamespace.ICart>,
              private route: ActivatedRoute) {
    this.paymentInfo = new AmexioCreditCardModel();
    this.cartInfo = new CartResponse();


    this._store.pipe(select(CartNamespace.getState)).subscribe((cartState: any) => {
      debugger;
      if (cartState) {
        this.cartInfo = <CartResponse>cartState.cartData;
      }

    });
  }


  ngOnInit() {
  }

  payHandle() {
debugger
    try {

      const requestBody = {
        "shoppintCart": this.createRequestBody()
      };


      this._httpService.restCall(SERVICE_URL.PAYMENT, 'post', requestBody).toPromise()
          .then((res: any) => {
        debugger;
            this._cService.showLoader = false;
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
