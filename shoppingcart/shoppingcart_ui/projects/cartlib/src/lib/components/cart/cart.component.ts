import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ICart} from '../../interface/cart.interface';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'lib-cartlib',
  templateUrl: 'cart.component.html'
})
export class CartComponent implements OnInit {
  cartInfo: ICart;
  constructor(private _httpClient: HttpClient, private _router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.getCartData();
  }

  getCartData() {
    this._httpClient.get('assets/data/cart.json').toPromise().then(
      (res: any) => {
        this.cartInfo = <ICart>res.data;
      });
  }

  payClickHandle(event: any) {
    this._router.navigate(['../payment'], {relativeTo: this.route});

  }

}
