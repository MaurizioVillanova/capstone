import { Prodottiread } from "./Prodottiread";
import { Prodotti } from "./prodotti";
import { User } from "./user";

export interface Preferito{
 // getPreferitiItems(): { [key: number]: Prodotti };

id: number,
user: User,

prodotto: Prodotti

}
