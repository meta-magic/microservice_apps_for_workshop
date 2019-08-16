import {ProductNamespace} from './state';
import {ProductActionType} from "./action";
import IProduct = ProductNamespace.IProduct;
/**
 * Created by dattaram on 12/8/19.
 */


export const INITIAL_STATE: ProductNamespace.IProduct = {
  productData: [],
  cartData: []
};

export function ProductReducer(state = INITIAL_STATE, action: any) {
  const operationClass = new OperationClass(state, action);
  switch (action.type) {
    case ProductActionType.updateProductData: {
      return {
        ...state,
        productData: action.data
      };
   }
   break;
    case ProductActionType.addToCart: {
      return {
        ...state,
        cartData: action.data
      };
    }
    break;
    case ProductActionType.addProduct: {
      return {
        ...state,
        productData: operationClass.addProduct()
      };
    }
  }
}


export class OperationClass {
  state: IProduct;
  action: any;
  constructor(_state: IProduct, _action: any) {
    this.state = _state;
    this.action = _action;
  }
  addCartData(): any[] {
    const cartLocal = Object.assign([], this.state.cartData);
    cartLocal.push(this.action.data);
    return cartLocal;
  }

   addProduct() {
    debugger;
     const productLocal = Object.assign([], this.state.cartData);
     productLocal.push(this.action.data);
     return productLocal;
  }
}




