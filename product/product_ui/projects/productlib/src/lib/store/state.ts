import {createFeatureSelector, createSelector} from "@ngrx/store";
/**
 * Created by dattaram on 12/8/19.
 */
export namespace ProductNamespace {
export interface IProduct{
  productData: any[];
  cartData: any[];
}

  export const productState =  createFeatureSelector<ProductNamespace.IProduct>('productState');

  export const getState = createSelector(
    productState,
    state => state
  );

}
