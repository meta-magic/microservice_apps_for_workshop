/**
 * Created by dattaram on 13/8/19.
 */
import {Component, OnInit} from '@angular/core';
import {SERVICE_URL} from "../../constant/service.constant";
import {ActivatedRoute, ParamMap, Router} from "@angular/router";
import {SharedService} from "sharedlib";
import {AddReview} from "../../models/review.model"
@Component({
  selector: 'product-detils', templateUrl: 'product-details.component.html'
})

export class ProductDetailsComponent implements OnInit {
  productDetails: any;
  showReviewWindow = false;
  addReviewModel:AddReview;
  public max: number = 5;
  constructor( private _router: Router,
               private _sharedService: SharedService,
               private _route: ActivatedRoute) {
                this.addReviewModel = new AddReview();
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
        this.addReviewModel.productId = this.productDetails.id;
      });
  }

  backClickHandle() {
    this._router.navigate(['../'], {relativeTo: this._route});
  }

  onAddReviewHandle() {
    this.showReviewWindow = true;
  }
  addHandle() {
    this._sharedService._httpService.restCall(SERVICE_URL.ADD_REVIEW, 'post', this.addReviewModel).toPromise()
        .then((res: any) => {
          this._sharedService._commonService.showLoader = false;
          this._sharedService._commonService.setInfoMsgCollection(res.message);
          this.productDetails.reviews.push(res.data);
          this.showReviewWindow = false;
        });
  }
}
