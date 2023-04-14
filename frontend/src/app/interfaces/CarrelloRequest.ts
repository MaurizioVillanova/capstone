import { elementoDelCarrello } from "./elementoDelCarrello";

export interface CarrelloRequest {
  cartItems: elementoDelCarrello[];
  totalCost: number;
}
