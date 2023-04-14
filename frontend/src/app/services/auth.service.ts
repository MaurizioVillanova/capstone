import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, ReplaySubject, Subject, tap } from 'rxjs';
import { AuthResponse, LoginRequest, RegisterRequest } from '../interfaces/auth-response';
import { Observable } from 'rxjs';
import { Prodotti } from '../interfaces/prodotti';
import { UserData } from '../interfaces/user-data';

import { environment } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';

import { Preferito } from '../interfaces/preferito';
import { User } from '../interfaces/user';
import { Carrello } from '../interfaces/carrello';
import { Carrelloread } from '../interfaces/carrelloread';
import { Cart } from '../interfaces/Cart';
import { CartProd } from '../interfaces/CartProd';
import { CarrelloRequest } from '../interfaces/CarrelloRequest';
import { Prodotto } from '../interfaces/Prodotto';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authSubject = new BehaviorSubject<null|User>(null);

  user$ = this.authSubject.asObservable()
  prodotti!: Prodotti;
  users: User[] =[];
idUser!:string|null;
idProdotto!:string|null;
idCarrello!: string|null;
loggedUser!:User;
idPreferito!:string|null;
  private apiServerUrl = environment.apiBaseUrl;
  private baseUrl = 'http://localhost:8080/api/carrello';
  private baseUrl2 = 'http://localhost:8080/api/carrello';
  private apiUrl3 = 'https://api.waifu.pics/many/waifu';
  private getUserEmailString = this.apiServerUrl + '/api/users/userInfo/{email}';
  private getUserAllString = this.apiServerUrl +'/api/users/all';
  private getUserIdString = this.apiServerUrl +`/api/users/`;
  private getFavoritoByIdString = this.apiServerUrl+`api/preferiti/{id}`;
  //private getUserString = this.apiServerUrl +'/api/users/{id}';
  private addCarrelloString = this.apiServerUrl +'api/carrello/aggiungiparam'
  private getFavoritiString = this.apiServerUrl+'/api/preferiti/all';
  private getFavoritiIdUserString = this.apiServerUrl+'/api/preferiti';
 // private addFavoritiString = this.apiServerUrl+'/api/preferiti/aggiungi';
  private deleteFavoritiString = this.apiServerUrl+'/api/preferiti/delete/{elementoId}/{userId}'
  private getProdottiString = this.apiServerUrl+'/api/prodotti';
  private getProdottiPagString = this.apiServerUrl+'/api/prodotti/Iapi/prodotti';
  private getFavoritiByIdString = this.apiServerUrl+`/api/preferiti/${this.idUser}`;
 private deleteProdottoDalCarrelloString = this.apiServerUrl + `/api/carrello/delete/`;
 private deleteProdottoDaiPreferitiString = this.apiServerUrl + `/api/preferiti/delete/`;
  private getProdottiByCategoriaPosterString = this.apiServerUrl+'/api/prodotti/categoria/POSTER';
  private getProdottiByCategoriaNarutoString = this.apiServerUrl+'/api/prodotti/categoria/NARUTO';
  private getProdottiByCategoriaMarvelString = this.apiServerUrl+'/api/prodotti/categoria/MARVEL';
  private getProdottiByCategoriaOneString = this.apiServerUrl+'/api/prodotti/categoria/ONE_PIECE';
  private getProdottiByCategoriaBundleString = this.apiServerUrl+'/api/prodotti/categoria/BUNDLE';
  private getProdottiByCategoriaStarString = this.apiServerUrl+'/api/prodotti/categoria/STAR_WARS';
  private getProdottiByCategoriaPoPString = this.apiServerUrl+'/api/prodotti/categoria/FUNKO_POP';
  private getProdottiByCategoriaBallString = this.apiServerUrl+'/api/prodotti/categoria/DRAGON_BALL';
  private getProdottiByCategoriaFigureString = this.apiServerUrl+'/api/prodotti/categoria/ACTION_FIGURE';
  private addProdottiString = this.apiServerUrl+'/api/prodotti/addprodotto';
  private getUserbyUsernameString = this.apiServerUrl+ '/api/users/username/{username}'
  private updateProdottiString = this.apiServerUrl+'/api/prodotti/update/{id}';
  private deleteProdottiString =   this.apiServerUrl+'/api/prodotti/delete/';

  constructor(private http:HttpClient) { }
getUtenteById(id:number){
  return this.http.get<User>(`${this.apiServerUrl}/api/users/${localStorage.getItem("id")}`)
}


getProdottoById(id: number){
  return this.http.get<Prodotti>(`${this.apiServerUrl}/api/prodotti/${id}`)
}
getUserByIdd(id:number){
  return this.http.get<User>(`${this.apiServerUrl}/api/users/${id}`)
}
getCartItems(userId: number): Observable<Carrelloread[]> {
  return this.http.get<Carrelloread[]>(`${this.apiServerUrl}/api/carrello/${userId}`);
}
getPreferitiItems(userId: number): any {
  return this.http.get(`${this.apiServerUrl}/api/preferiti/${userId}`);
}
deletePreferito(elementoId: number): Observable<any> {
  return this.http.delete<any>(`${this.apiServerUrl}/api/preferiti/${elementoId}`);
}
getUser (id : string) : Observable<User> {
  return this.http.get<User>(`${environment.apiBaseUrl}/api/users/${id}`);
}
public getElementiDelPreferito(userId: number): Observable<Carrello> {
  const url = `${this.apiServerUrl}api/preferito/${localStorage.getItem("id")}`;
  return this.http.get<Carrello>(url);
}
/*getUserCart (userId : string) : Observable<Carrelloread[]> {
  return this.http.get<Carrelloread[]>(`${environment.apiBaseUrl}/api/carrello/${userId}`)
}*/
getProdottiCarrello(userId: number): Observable<Prodotti[]> {
  const url = `${this.baseUrl2}/${userId}`;
  return this.http.get<Prodotti[]>(url);
}
public addFavoritiParam(idP: number, idU:number){
  const favorito={idProdotto: idP, IdUser: idU}
  const favoritoString = JSON.stringify(favorito)
return this.http.post(` http://localhost:8080/api/preferiti/aggiungiparam?idProdotto=${idP}&idUser=${localStorage.getItem("id")}`, favorito)
}
public addCarrelloParam(idP: number, idU:number, q:number){
 const carrello={idProdotto: idP, idUser: idU, quantity: q}
 const carrelloString= JSON.stringify(carrello)
 console.log(carrelloString)

  return this.http.post(`http://localhost:8080/api/carrello/aggiungiparam?idProdotto=${idP}&idUser=${localStorage.getItem("id")}&quantity=${q}`,carrello )
}


public getUserByUsername(): Observable<UserData[]>{
  return this.http.get<UserData[]>(this.getUserbyUsernameString)
}
public getFavoriti(): Observable<Preferito[]>{
return this.http.get<Preferito[]>(this.getFavoritiString,)
}
public findall(): Observable<User[]>{

  return this.http.get<User[]>(this.getUserAllString,{
    headers: new HttpHeaders({
    'Authorization': 'Bearer ' + localStorage.getItem('token'),
     'Content-Type': 'application/json'
   })})
}
public deleteProdottoDalCarrello(idUser:string, itemId: string): Observable<any>{
console.log(this.deleteProdottoDalCarrelloString + idUser + `/`+ itemId)
return this.http.delete(this.deleteProdottoDalCarrelloString + idUser + `/`+ itemId)
}
public deleteProdottoDaiPreferiti(idUser:string, idPreferito: string): Observable<any>{
  console.log(this.deleteProdottoDaiPreferitiString + idUser + `/`+ idPreferito)
  return this.http.delete(this.deleteProdottoDaiPreferitiString + idUser + `/`+ idPreferito);

  }
public getFavoritiById(): Observable<Preferito[]>{

  return this.http.get<Preferito[]>(this.getFavoritiIdUserString, {
    headers: new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('token'),
       'Content-Type': 'application/json'
     })})
}
public getFavoritoById(): Observable<Preferito[]>{

  return this.http.get<Preferito[]>(this.getFavoritiByIdString, {
    headers: new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('token'),
       'Content-Type': 'application/json'
     })})
}
/*public addFavoriti(prodotti:Prodotti): Observable<Preferito> {
  return this.http.post<Preferito>(
    this.addFavoritiString, prodotti
  );
}*/
public deleteFavoriti(favoritoId: number): Observable<void> {
  return this.http.delete<void>(
   this.deleteFavoritiString
  );
}
public getUserEmail(): Observable<UserData[]>{
return  this.http.get<UserData[]>(this.getUserEmailString)
}
getProductsByCategory(categoria: string): Observable<Prodotti[]> {
  return this.http.get<Prodotti[]>(`${this.apiServerUrl}/api/prodoti/categoria/${categoria}`);
}
createProdotto(prodotto: Prodotto): Observable<Prodotto> {
  return this.http.post<Prodotto>(`${this.apiServerUrl}/api/prodotti/addprodotto`, prodotto);
}

/*public getUser(): Observable<User[]>{
  return this.http.get<User[]>(this.getUserString);
}*/
  public getProdotti(): Observable<Prodotti[]>{
    return this.http.get<Prodotti[]>(this.getProdottiString);
  }
  public getProdottiPag(): Observable<Prodotti[]>{
    return this.http.get<Prodotti[]>(this.getProdottiPagString);
  }
  getCarrello(userId: number): Observable<CarrelloRequest> {
    return this.http.get<CarrelloRequest>(`${this.baseUrl}/${userId}`);
  }

  public getProdottiByCategoriaStar(): Observable<Prodotti[]>{
    return this.http.get<Prodotti[]>(this.getProdottiByCategoriaStarString);
    }
  public getProdottiByCategoriaOne(): Observable<Prodotti[]>{
    return this.http.get<Prodotti[]>(this.getProdottiByCategoriaOneString);
    }
  public getProdottiByCategoriaNaruto(): Observable<Prodotti[]>{
    return this.http.get<Prodotti[]>(this.getProdottiByCategoriaNarutoString);
    }
  public getProdottiByCategoriaMarvel(): Observable<Prodotti[]>{
    return this.http.get<Prodotti[]>(this.getProdottiByCategoriaMarvelString);
    }
  public getProdottiByCategoriaBundle(): Observable<Prodotti[]>{
    return this.http.get<Prodotti[]>(this.getProdottiByCategoriaBundleString);
    }
   /* public deleteElement(elementoId: Prodotti, userId: User): Observable<Carrello> {
      const url = `${this.apiServerUrl}/delete/${elementoId.idProdotto}/${userId.idUser}`;
      return this.http.delete<Carrello>(url);
    }*/
    public deleteElement(elementoId: number, userId: number): Observable<Carrello> {
      const carrello={idProdotto: elementoId, idUser: userId}
 const carrelloString= JSON.stringify(carrello)
      const url = `${this.apiServerUrl}/delete/${elementoId}/${userId}`;
      return this.http.delete<Carrello>(url);
    }
  public getProdottiByCategoriaFigure(): Observable<Prodotti[]>{
    return this.http.get<Prodotti[]>(this.getProdottiByCategoriaFigureString);
    }
  public getProdottiByCategoriaBall(): Observable<Prodotti[]>{
    return this.http.get<Prodotti[]>(this.getProdottiByCategoriaBallString);
    }
  public getProdottiByCategoriaPoster(): Observable<Prodotti[]>{
  return this.http.get<Prodotti[]>(this.getProdottiByCategoriaPosterString);
  }  public getProdottiByCategoriaPoP(): Observable<Prodotti[]>{
    return this.http.get<Prodotti[]>(this.getProdottiByCategoriaPoPString);
    }
  public addProdotti(prodotto: any) {
    const pr = {
      "prezzo": 6.99,
          "nome": "One Piece Buggy Bounty Poster",
          "immagineUrl": "https://i0.wp.com/redhawknerd.com/wp-content/uploads/2022/10/poster-Copia-2.jpg?fit=1000%2C1000&ssl=1",
          "descrizione": "(28,6 x 43,9 cm)",
          "categoria": "POSTER",
          "disponibilita": "DISPONIBILE"}
console.log("prodotto aggiunto", prodotto)
return this.http.post<any>(
  this.addProdottiString, prodotto
);
}

public updateProdotto(prodotto:Prodotti): Observable<Prodotti> {
return this.http.put<Prodotti>(this.updateProdottiString, prodotto);
}
public deleteProdotto(prodottoId: number): Observable<void> {
return this.http.delete<void>(
 this.deleteProdottiString+ prodottoId
);
}
mySubj = new Subject();
 loggedSubj = new ReplaySubject<false|AuthResponse["user"]>();
 username = new BehaviorSubject<string | null>(localStorage.getItem('username'))
registerUrl = this.apiServerUrl +'/api/users/adduser'
loginUrl = this.apiServerUrl +'/auth/login'
  register(data:RegisterRequest) {
    return this.http.post<AuthResponse>(this.registerUrl, data).pipe(catchError((err)=>{
      console.log(err);
      throw err
    }))
  }

  login(data:LoginRequest) {
    return this.http.post<AuthResponse>(this.loginUrl, data).pipe(catchError(err=>{
      console.log(err);

      throw err;
    }), tap((res)=>{
      console.log(res)
      console.log( res.token)
      localStorage.setItem("token", res.token)
      localStorage.setItem("id", res.id.toString())
      localStorage.setItem("roles", res.roles.toString())
     // this.setCart()
      this.loggedSubj.next(res.user)
    }))
  }

  logout(){
    localStorage.removeItem("token")
    this.loggedSubj.next(false)
  }

  getLoggedObs() {
    console.log(this.getLoggedObs)
    return this.loggedSubj.asObservable()
  }

 getUserById(id:number, token:string) {
   return this.http.get<AuthResponse['user']>(this.getUserAllString , {
      headers:{
        "Authorization": "Bearer " + token
      }
    }).pipe(tap((res)=>{
      this.loggedSubj.next(res)
    }))
  }
  getUserById2() {
    this.idUser= "1";
    return this.http.get<User>(this.getUserIdString+"1", {
       headers:{
         "Authorization": "Bearer " + localStorage.getItem('token')
       }
     }).subscribe({

     next:res=>{
      console.log("getusersById2", res)
      this.loggedUser = res;
      // this.loggedSubj.next(res)
     },
     error: error=>console.log(error),
     complete:()=>console.log("ok, completato!")
    })
   }


  autologin() {
    let token = localStorage.getItem("token")
    if(token) {
      //TODO: leggere id con JWTHelper
      this.getUserById(0, token)
    }
  }
  getUserByToken():Observable<User>{
    return this.http.get<User>(`${environment.apiBaseUrl}/api/users/`, {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
        'Content-Type': 'application/json'
      })
    })
  }


/*public  getUser(): Observable<User[]>{
  return this.http.get<User[]>(this.getUserString);
}*/
/*createToken (username : string) : Observable<Token> {
  return this.http.post<Token>(`${environment.apiBaseUrl}/api/create-token`, {
      username
  });
}*/

/*public removeFromCart(prodottoId: number): void {
  const cart = this.getCarrello();
  const index = cart.shoes.findIndex(s => s.shoe.id === shoeId);
  if (index !== -1) {
    // se la scarpa è nel carrello, la rimuovo
    const shoe = cart.shoes[index];
    if (shoe.sizes.length > 0) {
      const sizeIndex = shoe.sizes.findIndex(s => s.size === cart.shoes[index].sizes[0].size);
      if (sizeIndex !== -1 && cart.shoes[index].sizes[sizeIndex].quantityOrdered > 0) {
        cart.shoes[index].sizes[sizeIndex].quantityOrdered--;
        cart.totalPrice -= cart.shoes[index].sizes[sizeIndex].size.price; // sottrai il prezzo della taglia dal prezzo totale
      }
    }
    // se non ci sono più scarpe rimuovo la scarpa dal carrello
    if (shoe.sizes.length === 0 || shoe.sizes.every(size => size.quantityOrdered === 0)) {
      cart.shoes.splice(index, 1);
    }
    // salvo il carrello aggiornato nella sessionStorage
    this.setCart(cart);
  }
}*/


}
