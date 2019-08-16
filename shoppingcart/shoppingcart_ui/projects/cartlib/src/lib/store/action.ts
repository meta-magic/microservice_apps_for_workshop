/**
 * Created by dattaram on 12/8/19.
 */

import {Action} from 'redux';
import {CartResponse} from "../models/cart.model";
export enum CartActionType {
  addToShoopingCart = 'addToShoopingCart'
}

export class AddToShoopingCart implements Action {
  readonly type = CartActionType.addToShoopingCart;
  constructor(public data: CartResponse) {}
}
