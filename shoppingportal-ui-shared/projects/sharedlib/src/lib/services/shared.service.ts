/**
 * Created by dattaram on 14/8/19.
 */
import {Injectable} from '@angular/core';
import {CommonService} from "./common.service";
import {HttpService} from "./http.service";
import {CookieService} from "ngx-cookie-service";

@Injectable()
export class SharedService {

  constructor(public _commonService: CommonService,
              public _httpService: HttpService,
              public _cookieService: CookieService) {
  }
}
