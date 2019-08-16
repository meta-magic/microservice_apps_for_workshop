import {PaymentActionType} from "./action";
import {PaymentNamespace} from "./state";
/**
 * Created by dattaram on 12/8/19.
 */


export const INITIAL_STATE: PaymentNamespace.IPayment = {
  cartData: []
};

export function PaymentReducer(state = INITIAL_STATE, action: any) {
  switch (action.type) {
    case PaymentActionType.addToCart: {
      return {
        ...state,
        cartData: action.data
      };
    }
  }
}



