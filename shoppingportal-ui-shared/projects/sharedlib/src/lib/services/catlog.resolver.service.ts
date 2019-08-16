/**
 * Created by dattaram on 16/8/19.
 */
import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {HttpService} from "./http.service";
import {SERVICE_URL} from '../constant/service.constant';
import { Observable, of, EMPTY }  from 'rxjs';
import { mergeMap, take }         from 'rxjs/operators';
import {CommonService} from "./common.service";


@Injectable()
export class CatlogResolverService implements Resolve<any>{

  constructor(private _httpService: HttpService, private _commonService: CommonService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> {
  return this.getProduct();
  }

  getProduct(): Observable<any> {
   return this._httpService.restCall(SERVICE_URL.GET_ALL_PRODUCT, 'get').pipe(
     take(1),
     mergeMap((res: any)=>{
       if(res) {
         this._commonService.showLoader = false;
         return of(res);
       } else {
         return EMPTY;
       }

     })
   )
  };


}
