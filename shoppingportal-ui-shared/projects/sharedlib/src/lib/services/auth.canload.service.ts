/**
 * Created by dattaram on 16/8/19.
 */
import {Injectable} from '@angular/core';
import { CanLoad, Route, Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";

@Injectable()
export class AuthCanLoadService implements CanLoad {
  constructor(private _cookieService: CookieService, private router: Router){}

  canLoad(route: Route) {
    debugger;
    return this.checkTokenId();

  }

  checkTokenId(): boolean {
    if(this._cookieService.check('tokenId')) {
      return true;
    } else  {
      this.router.navigate(['']);
      return false;
    }
  }
}



