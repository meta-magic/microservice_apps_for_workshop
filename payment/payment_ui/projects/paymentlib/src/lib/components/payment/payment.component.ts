/**
 * Created by dattaram on 12/8/19.
 */
import {Component, OnInit} from '@angular/core';
import { AmexioCreditCardModel } from 'amexio-ng-extensions';
import {ActivatedRoute, Router} from "@angular/router";
import {SERVICE_URL} from "../../constant/service.constant";
import {SharedService} from "sharedlib";
import {Store} from "@ngrx/store";
import {AddToCart} from "../../store/action";
import {PaymentNamespace} from "../../store/state";

@Component({
  selector: 'payment',
  templateUrl: 'payment.component.html'
})

export class PaymentComponent implements OnInit {
  paymentInfo: AmexioCreditCardModel;
  cartInfo: any;
  constructor(private _router: Router,
              private _sharedService: SharedService,
              private store: Store<PaymentNamespace.IPayment>,
              private route: ActivatedRoute) {
    this.paymentInfo = new AmexioCreditCardModel();
  }


  ngOnInit() {
    this.getCartData();
  }

  getCartData() {
    this._sharedService._httpService.restCall(SERVICE_URL.GET_CART_PRODUCT, 'get').toPromise()
      .then((res: any) => {
        this._sharedService._commonService.showLoader = false;
        this.cartInfo = res.data;
      });
  }

  payHandle() {
    try {
      const requestBody = {
        "shoppintCart": this.createRequestBody()
      };
      this._sharedService._httpService.restCall(SERVICE_URL.PAYMENT, 'post', requestBody).toPromise()
          .then((res: any) => {
            this._sharedService._commonService.showLoader = false;
            this._sharedService._commonService.setInfoMsgCollection(res.message);
            this.getCart();
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

  getCart() {
    debugger;
    this._sharedService._httpService.restCall(SERVICE_URL.GET_CART_PRODUCT, 'get').toPromise()
      .then((res: any) => {
        this.store.dispatch(new AddToCart(res.data.shoppingCart));
      });
  }
}
