import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'lib-orderslib',
  templateUrl: 'orderslib.component.html'
})
export class OrderslibComponent implements OnInit {

  ordersList: any[] = [];

  constructor(private _httpClient: HttpClient) { }

  ngOnInit() {
    this.getOrdersDetails();
  }

  getOrdersDetails() {
    this._httpClient.get('assets/data/orders.json').subscribe((res: any) => {
      this.ordersList = res.response;
    });
  }

}
