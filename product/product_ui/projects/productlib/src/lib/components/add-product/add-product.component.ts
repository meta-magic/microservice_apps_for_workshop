import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product.model";
import {CommonService} from "../../services/common.service";
import {HttpService} from "../../services/http.service";
import {SERVICE_URL} from "../../constant/service.constant";
import {ActivatedRoute, Router} from "@angular/router";
import {Store} from "@ngrx/store";
import {ProductNamespace} from "../../store/state";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html'
})
export class AddProductComponent implements OnInit {
  product: Product;
  constructor(public _cService: CommonService,
              private _router: Router,
              private _route: ActivatedRoute,
              private _store: Store<ProductNamespace.IProduct>,
              private _httpService: HttpService) {
    this.product = new Product();
  }

  ngOnInit() {
  }

  addProductHandle() {
    // this._store.dispatch(new AddProduct(this.product));
   // this._router.navigate(['../catlog'], {relativeTo: this._route});

    try {
      this._httpService.restCall(SERVICE_URL.ADD_PRODUCT, 'post', this.product).toPromise()
        .then((res: any) => {
          this._cService.showLoader = false;
          this._cService.infoMsgCollection = [];
          this._cService.infoMsgCollection.push(res.message);
          this.product = new Product();
          this._router.navigate(['../catlog'], {relativeTo: this._route});

        });
    } catch (error) {
    }
  }

}
