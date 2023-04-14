import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Preferito } from "src/app/interfaces/preferito";
import { Prodotti } from "src/app/interfaces/prodotti";
import { Prodottiread } from "src/app/interfaces/Prodottiread";
import { User } from "src/app/interfaces/user";
import { AuthService } from "src/app/services/auth.service";
import { FormsModule } from "@angular/forms";
@Component({
    selector: "app-prodottospecifico",
    templateUrl: "./prodottospecifico.component.html",
    styleUrls: ["./prodottospecifico.component.scss"],
})
export class ProdottospecificoComponent implements OnInit {
    quantity: number = 1;
    prodotti!: Prodotti;
    favoriti!: Preferito;
    localStorage!: Storage;
    idProdottospecifico!: number;
    public elementoId!: Prodotti;
    public userId!: User;
    roles: any;
    logged = false;
    user!: User;
    disabledButton=false;
    constructor(
        private authSrv: AuthService,
        private route: ActivatedRoute,
        private http: HttpClient,
        private r:Router
    ) {
        this.localStorage = window.localStorage;
    }

    ngOnInit(): void {
        console.log(localStorage.getItem("token"));
        if (localStorage.getItem("token") != null) {
            this.logged = true;
        } else {
            this.logged = false;
        }
        const id = +this.route.snapshot.paramMap.get("id")!;
        this.authSrv.getProdottoById(id).subscribe({
            next: (prodotti) => (this.prodotti = prodotti),
            error: (error) => console.log(error),
            complete: () => console.log("ok"),
        });
        this.roles = this.localStorage.getItem("roles");
    }
    isLiked(): boolean {
        return this.favoriti ? true : false;
    }
    aggiungiCarrello(idP: number, idU: number, q: number) {
        this.authSrv.addCarrelloParam(idP, idU, q).subscribe({
            next: (data) => console.log(data),
            error: (error) => console.log("aggiungiCarrello ", error),
            complete: () => console.log("aggiungiCarrello completato")
           
        });
    }
    /* rimuoviDalCarrello(idP: number, idU: number){
       this.authSrv.deleteElement(idP, idU).subscribe(
        {
            next: data=>console.log(data),
            error: error=>console.log("rimuoviDalCarrello ", error),
            complete: ()=>console.log("rimuoviDalCarrello completato")  
        }
       )
     }*/
    aggiungiAiPreferiti(idP: number, idU: number) {
        this.authSrv.addFavoritiParam(idP, idU).subscribe({
            next: (data) =>{
                console.log(data);
                this.disabledButton = true;
            } ,
            error: (error) => console.log("aggiungiAiPreferiti ", error),
            complete: () => console.log("aggiungiAiPreferiti completato"),
        });
    }
    increaseQuantity() {
        this.quantity++;
    }

    decreaseQuantity() {
        if (this.quantity > 1) {
            this.quantity--;
        }
    }
    rimuoviProdotto(idProdotto: any) {
        this.authSrv.deleteProdotto(idProdotto).subscribe({
            next: (data) => console.log(data),
            error: (error) => console.log("rimuoviProdotto ", error),
            complete: () => console.log("rimuoviProdotto completato"),
           
        });
    }
    rimuoviPreferito(userId: string, idPreferito: string){
        this.authSrv.deleteProdottoDaiPreferiti(userId, idPreferito).subscribe({
            next:  response => {
                console.log(response)
              },
              error:e =>console.log(e),
              complete: ()=>{
                  console.log('rimuoviPreferito complete');
                  
              }
              });
      }
    /*  public rimuoviDalCarrello(): void {
        this.authSrv.deleteElement( )
          .subscribe(
            response => {
              console.log('Elemento eliminato dal carrello');
              // Aggiornare la lista degli elementi nel carrello dopo l'eliminazione
            },
            error => {
              console.error('Errore durante l\'eliminazione dell\'elemento dal carrello', error);
            }
          );
      }*/
      go(){
        this.r.navigate(['/'])
      }
}
