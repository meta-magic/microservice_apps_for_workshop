import {createFeatureSelector, createSelector} from "@ngrx/store";
/**
 * Created by dattaram on 12/8/19.
 */
export namespace PaymentNamespace {
export interface IPayment{
  cartData: any[];
}

  export const paymentState =  createFeatureSelector<PaymentNamespace.IPayment>('paymentState');

  export const getState = createSelector(
    paymentState,
    state => state
  );

}
