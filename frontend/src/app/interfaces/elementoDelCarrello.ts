import { Prodotti } from "./prodotti";

export interface elementoDelCarrello{
  id: number,
  quantity: number,
  prodotto: Prodotti[]
}
