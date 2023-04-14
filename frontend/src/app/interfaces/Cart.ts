import { Prodotti } from "./prodotti";

export interface Cart{
  userId: number,
  prodotti: Prodotti[],
  totalPrice: number
}
