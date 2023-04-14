import { Carrelloread } from "./carrelloread";

export interface OrderRequest {
  userId: number,
  prodotti: Carrelloread[]
}
