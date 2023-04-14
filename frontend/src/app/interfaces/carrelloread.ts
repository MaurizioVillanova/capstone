import { Prodotti } from "./prodotti"

import { User } from "./user"

export interface Carrelloread{
cartItems: [
  id: number,
  quantity: number,
  prodotto: Prodotti
],
totalCost: number
  }
