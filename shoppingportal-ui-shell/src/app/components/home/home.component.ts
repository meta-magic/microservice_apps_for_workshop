import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Store} from "@ngrx/store";
import {SharedService} from "sharedlib";


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  cartCount = 0;
  constructor(private _router: Router,
              private _sharedService: SharedService,
              private store: Store<any>) {
  }

  ngOnInit() {
    this.store.select<any>('productState').subscribe((customerState: any) => {
      if(customerState && customerState.cartData.length > 0) {
        this.cartCount = customerState.cartData.length;
      }
      });

    this.store.select<any>('paymentState').subscribe((paymentState: any) =>  {

      if(paymentState && paymentState.cartData) {
        this.cartCount = paymentState.cartData.length;
      }
    });

    this.store.select<any>('cartState').subscribe((cartState: any) =>  {

      if(cartState && cartState.cartData && cartState.cartData.hasOwnProperty('shoppingCart')) {
        this.cartCount = cartState.cartData.shoppingCart.length;
      }
    });



  }

  logOutHandle(event: any) {
    this._sharedService._cookieService.delete('tokenId');
    this._sharedService._commonService.setInfoMsgCollection('Logout Successfully.');
    this._router.navigate(['login']);
  }


  addProductHandle(event: any) {
    this._router.navigate(['home/product/add-product']);
  }

  orderHandle(event: any) {
    this._router.navigate(['home/order']);
  }

  catlogHandle(event: any) {
    this._router.navigate(['home/product/catlog']);
  }

  cartHandle(event: any) {
    this._router.navigate(['home/cart']);

  }
}
