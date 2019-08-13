/**
 * Created by dattaram on 12/8/19.
 */

import {Action} from 'redux';
import {Product} from '../models/product.model';
export enum ProductActionType {
  updateProductData = 'updateProductData',
  addToCart = 'addToCart',
  addProduct = 'addProduct'
}


export class UpdateProductData implements Action {
  readonly type = ProductActionType.updateProductData;
  constructor(public data: any[]) {}
}

export class AddToCart implements Action {
  readonly type = ProductActionType.addToCart;
  constructor(public data: any[]) {}
}

export class AddProduct implements Action {
  readonly type = ProductActionType.addProduct;
  constructor(public data: Product) {}
}
