/**
 * Created by dattaram on 13/8/19.
 */
import {Component, OnInit} from '@angular/core';
import {SERVICE_URL} from "../../constant/service.constant";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {SharedService} from "sharedlib";

@Component({
  selector: 'product-detils', templateUrl: 'product-details.component.html'
})

export class ProductDetailsComponent implements OnInit {
  productDetails: any;
  constructor( private _router: Router,
               private _sharedService: SharedService,
               private _route: ActivatedRoute) {
  }

  ngOnInit() {
    this._route.paramMap.subscribe((param: ParamMap) => {
      if (param.get('id') != null) {
        this.getProductDetails(param.get('id'));
      }
    });
  }

  getProductDetails(id: string) {
    this._sharedService._httpService.restCall(SERVICE_URL.GET_ALL_PRODUCT + id, 'get').toPromise()
      .then((res: any) => {
        this._sharedService._commonService.showLoader = false;
        this.productDetails = res.data;
      });
  }

  backClickHandle() {
    this._router.navigate(['../'], {relativeTo: this._route});
  }
}
