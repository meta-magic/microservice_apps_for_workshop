/**
 * Created by dattaram on 13/8/19.
 */
import {Component, OnInit} from '@angular/core';
import {HttpService} from "../../services/http.service";
import {CommonService} from "../../services/common.service";
import {SERVICE_URL} from "../../constant/service.constant";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";

@Component({
  selector: 'product-detils', templateUrl: 'product-details.component.html'
})

export class ProductDetailsComponent implements OnInit {
  productDetails: any;
  constructor( private _router: Router,
               private _route: ActivatedRoute,
               private _httpService: HttpService,
               public _cService: CommonService) {
  }

  ngOnInit() {
    this._route.paramMap.subscribe((param: ParamMap) => {
      if (param.get('id') != null) {
        this.getProductDetails(param.get('id'));
      }
    });
  }

  getProductDetails(id: string) {
    this._httpService.restCall(SERVICE_URL.GET_ALL_PRODUCT + id, 'get').toPromise()
      .then((res: any) => {
        this._cService.showLoader = false;
        this.productDetails = res.data;
      });
  }

  backClickHandle() {
    this._router.navigate(['../'], {relativeTo: this._route});
  }
}
