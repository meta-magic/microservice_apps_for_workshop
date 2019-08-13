import { Component, OnInit } from '@angular/core';
import {HttpService} from "../services/http.service";
import {CommonService} from "../services/common.service";
import {SERVICE_URL} from "../constant/service.constant";

@Component({
  selector: 'orders',
  templateUrl: 'orders.component.html'
})
export class OrdersComponent implements OnInit {

  ordersList: any[] = [];

  constructor(
      private _httpService: HttpService,
      public _cService: CommonService) { }

  ngOnInit() {
      debugger;
    this.getOrdersDetails();
  }

  getOrdersDetails() {
    this._httpService.restCall(SERVICE_URL.GET_ALL_ORDERS, 'get').toPromise()
        .then((res: any) => {
        debugger;
          this._cService.showLoader = false;
          this.ordersList = res.data;
        });
  }

}
