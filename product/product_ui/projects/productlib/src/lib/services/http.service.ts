import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs/index';
import {retry, catchError} from "rxjs/internal/operators";
import {CookieService} from "ngx-cookie-service";
import {CommonService} from "./common.service";


@Injectable({
    providedIn: 'root'
})
export class HttpService {

    constructor(private http: HttpClient,
                private _cService: CommonService,
                private _cookieService: CookieService) {

    }



    restCall(serviceUrl: string, methodType: string, requestJson?: any): Observable<any> {
       this._cService.showLoader = true;
        const headers = new HttpHeaders().append('Content-Type', 'application/json').append('tokenid', this._cookieService.get('tokenId'));
        if (methodType.toLowerCase() === 'post') {
          return this.http.post(serviceUrl, requestJson, {headers})
            .pipe(retry(2), catchError(this.handleError));
        } else if (methodType.toLowerCase() === 'get') {
          return this.http.get(serviceUrl, {headers})
            .pipe(retry(2), catchError(this.handleError));
        }
      }


  private handleError(error: HttpErrorResponse) {
    this._cService.showLoader = false;
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  }

}
