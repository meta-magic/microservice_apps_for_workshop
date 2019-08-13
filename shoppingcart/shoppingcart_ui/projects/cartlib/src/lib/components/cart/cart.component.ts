import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CartResponse} from '../../models/cart.model';
import {ActivatedRoute, Router} from '@angular/router';
import {SERVICE_URL} from "../../constant/service.constant";
import {HttpService} from "../../services/http.service";
import {CommonService} from "../../services/common.service";

@Component({
  selector: 'lib-cartlib',
  templateUrl: 'cart.component.html'
})
export class CartComponent implements OnInit {
  cartInfo: CartResponse;
  constructor(  private _httpService: HttpService,
                public _cService: CommonService,
                private _router: Router,
                private route: ActivatedRoute) {
    this.cartInfo = new CartResponse();
  }

  ngOnInit() {
    debugger;
    this.getCartData();
  }

  getCartData() {
    this._httpService.restCall(SERVICE_URL.GET_CART_PRODUCT, 'get').toPromise()
        .then((res: any) => {
          this._cService.showLoader = false;
          debugger;
          this.cartInfo = <CartResponse>res.data;
        });
  }

  payClickHandle(event: any) {
    this._router.navigate(['../payment'], {relativeTo: this.route});

  }

}
