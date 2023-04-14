import { Prodotti } from "./prodotti"
export interface Paging {
  content: Prodotti[],
  empty: boolean,
  first: boolean,
  last: boolean,
  number: number,
  numberOfElements: number,
  pageable: {},
  size: number,
  sort: {},
  totalElements: number,
  totalPages: number
}
