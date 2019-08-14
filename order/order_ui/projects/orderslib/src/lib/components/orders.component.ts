import { Component, OnInit } from '@angular/core';
import {SERVICE_URL} from "../constant/service.constant";
import {SharedService} from "sharedlib";

@Component({
  selector: 'orders',
  templateUrl: 'orders.component.html'
})
export class OrdersComponent implements OnInit {

  ordersList: any[] = [];
  hasRecordFound = true;

  constructor(private _sharedService: SharedService) { }

  ngOnInit() {
    this.getOrdersDetails();
  }

  getOrdersDetails() {
    this._sharedService._httpService.restCall(SERVICE_URL.GET_ALL_ORDERS, 'get').toPromise()
        .then((res: any) => {
          this._sharedService._commonService.showLoader = false;
          this.ordersList = res.data;
          this.hasRecordFound = this.ordersList.length > 0 ? true : false;
        });
  }

}
