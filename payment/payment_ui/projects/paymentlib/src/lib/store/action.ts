/**
 * Created by dattaram on 12/8/19.
 */

import {Action} from 'redux';
export enum PaymentActionType {
  addToCart = 'addToCart'
}

export class AddToCart implements Action {
  readonly type = PaymentActionType.addToCart;
  constructor(public data: any[]) {}
}
