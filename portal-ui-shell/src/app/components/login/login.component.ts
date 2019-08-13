import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {LoginModel} from "../../models/login.model";
import {HttpService} from "../../services/http.service";
import {SERVICE_URL} from "../../constant/service.constant";
import {HttpClient} from "@angular/common/http";
import {CookieService} from 'ngx-cookie-service';
import {CommonService} from "../../services/common.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginModel: LoginModel;

  constructor(
    private _route: Router,
    private _cService: CommonService,
    private _cookieService: CookieService,
    private _httpService: HttpService) {
    this.loginModel = new LoginModel();
  }

  ngOnInit() {
  }

  onLoginHandle() {
    try {
      this._httpService.restCall(SERVICE_URL.AUTH, 'post', this.loginModel).toPromise()
        .then((res: any) => {
          this._cService.showLoader = false;
          if (res.data.valid) {
          this._cookieService.set('tokenId', res.data.tokenId);
          this._route.navigate(['home']);
        }
        });
    } catch (error) {
    }
  }
  onAddUserHandle() {
    this._route.navigate(['add-user']);
  }
}
