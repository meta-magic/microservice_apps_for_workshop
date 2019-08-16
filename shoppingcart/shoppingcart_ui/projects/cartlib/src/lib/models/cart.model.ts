/**
 * Created by dattaram on 12/8/19.
 */
export class CartResponse {
  sum: number;
  shoppingCart: any[] = [];
  constructor() {
    this.sum = 0;
    this.shoppingCart = [];
  }
}
