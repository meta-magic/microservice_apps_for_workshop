import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {LoginModel} from "../../models/login.model";
import {SERVICE_URL} from "../../constant/service.constant";
import {SharedService} from "sharedlib";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginModel: LoginModel;

  constructor(
    private _route: Router,
    private route: ActivatedRoute,
    private _sharedService: SharedService) {
    this.loginModel = new LoginModel();
  }

  ngOnInit() {
  }

  onLoginHandle() {
    try {
      this._sharedService._httpService.restCall(SERVICE_URL.AUTH, 'post', this.loginModel).toPromise()
        .then((res: any) => {
          this._sharedService._commonService.showLoader = false;
          if (res.data.valid) {
          this._sharedService._cookieService.set('tokenId', res.data.tokenId);
          this._route.navigate(['home']);
        }
        });
    } catch (error) {
    }
  }
  onAddUserHandle() {
    debugger;
    this._route.navigate(['../add-user'],{ relativeTo: this.route });
  }
}
