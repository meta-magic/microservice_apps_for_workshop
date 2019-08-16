/**
 * Created by dattaram on 16/8/19.
 */
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {CookieService} from "ngx-cookie-service";

@Injectable()
export class AuthCanActiveService implements CanActivate {
  constructor(private _cookieService: CookieService, private router: Router){}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
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



