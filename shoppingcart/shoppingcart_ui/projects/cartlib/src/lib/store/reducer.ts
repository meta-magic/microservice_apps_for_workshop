import {CartActionType} from "./action";
import {CartNamespace} from "./state";
import {CartResponse} from "../models/cart.model";
/**
 * Created by dattaram on 12/8/19.
 */


export const INITIAL_STATE: CartNamespace.ICart = {
  cartData: new CartResponse()
};

export function CartReducer(state = INITIAL_STATE, action: any) {
  switch (action.type) {
    case CartActionType.addToShoopingCart: {
      return {
        ...state,
        cartData: action.data
      };
    }
  }
}



