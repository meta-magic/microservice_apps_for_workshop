import {createFeatureSelector, createSelector} from "@ngrx/store";
/**
 * Created by dattaram on 12/8/19.
 */
export namespace CartNamespace {
export interface ICart{
  cartData: any[];
}

  export const cartState =  createFeatureSelector<CartNamespace.ICart>('cartState');

  export const getState = createSelector(
      cartState,
    state => state
  );

}
