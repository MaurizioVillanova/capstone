import { Prodotti } from "./prodotti";
import { User } from "./user";

export interface Carrello{
id:number,
idProdotto:number,
idUser: number,
quantity: number
prodotti: Prodotti[],
utente: User
}
