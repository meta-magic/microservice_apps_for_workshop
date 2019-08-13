import {CartActionType} from "./action";
import {CartNamespace} from "./state";
/**
 * Created by dattaram on 12/8/19.
 */


export const INITIAL_STATE: CartNamespace.ICart = {
  cartData: []
};

export function CartReducer(state = INITIAL_STATE, action: any) {
  switch (action.type) {
    case CartActionType.addToCart: {
      return {
        ...state,
        cartData: action.data
      };
    }
  }
}



