import {createFeatureSelector, createSelector} from "@ngrx/store";
import {CartResponse} from "../models/cart.model";
/**
 * Created by dattaram on 12/8/19.
 */
export namespace CartNamespace {
export interface ICart{
  cartData: CartResponse;
}

  export const cartState =  createFeatureSelector<CartNamespace.ICart>('cartState');

  export const getState = createSelector(
      cartState,
    state => state
  );

}
