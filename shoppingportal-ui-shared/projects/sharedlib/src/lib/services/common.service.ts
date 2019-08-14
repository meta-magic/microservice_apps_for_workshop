/**
 * Created by dattaram on 13/8/19.
 */
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CommonService {
  get infoMsgCollection(): string[] {
    return this._infoMsgCollection;
  }

  setInfoMsgCollection(value: string) {
    debugger;
    this._infoMsgCollection = [];
    this._infoMsgCollection.push(value);
  }

  showLoader = false;
  private _infoMsgCollection: string[] = [];

  constructor() {
  }



}
