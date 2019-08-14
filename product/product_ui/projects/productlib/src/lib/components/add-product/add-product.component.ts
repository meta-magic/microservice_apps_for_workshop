import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product.model";
import {SERVICE_URL} from "../../constant/service.constant";
import {ActivatedRoute, Router} from "@angular/router";
import {SharedService} from "sharedlib";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html'
})
export class AddProductComponent implements OnInit {
  product: Product;
  constructor(private _sharedService: SharedService,
              private _router: Router,
              private _route: ActivatedRoute) {
    this.product = new Product();
  }

  ngOnInit() {
  }

  addProductHandle() {
    try {
      this._sharedService._httpService.restCall(SERVICE_URL.ADD_PRODUCT, 'post', this.product).toPromise()
        .then((res: any) => {
          this._sharedService._commonService.showLoader = false;
          this._sharedService._commonService.setInfoMsgCollection(res.message);
          this.product = new Product();
          this._router.navigate(['../catlog'], {relativeTo: this._route});

        });
    } catch (error) {
    }
  }

}
