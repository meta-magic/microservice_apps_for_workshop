/**
 * Created by dattaram on 12/8/19.
 */
import {Component, OnInit} from '@angular/core';
import { AmexioCreditCardModel } from 'amexio-ng-extensions';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'payment',
  templateUrl: 'payment.component.html'
})

export class PaymentComponent implements OnInit {
  paymentInfo: AmexioCreditCardModel;
  constructor(private _router: Router, private route: ActivatedRoute) {
    this.paymentInfo = new AmexioCreditCardModel();
  }


  ngOnInit() {
  }

  payHandle() {
    this._router.navigate(['../../order'], {relativeTo: this.route});
  }
}
