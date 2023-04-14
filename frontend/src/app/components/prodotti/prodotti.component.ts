import { Component, Input, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Prodotti } from '../../interfaces/prodotti';
import { AuthService } from '../../services/auth.service';
import { Preferito } from 'src/app/interfaces/preferito';
import { ActivatedRoute, Router } from '@angular/router';
import { Page } from 'src/app/interfaces/page';
import { Prodottiread } from 'src/app/interfaces/Prodottiread';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-prodotti',
  templateUrl: './prodotti.component.html',
  styleUrls: ['./prodotti.component.scss']
})
export class ProdottiComponent implements OnInit {
   
  p: number = 0;
  size: number = 10;
  totalPages!: number;
  public prodotti!: Prodotti[];
 favoriti! : Preferito;
 public term !: string
 localStorage!: Storage;
 prodottiread!: Prodottiread[]
 disabledButton=false;
  constructor(private http: HttpClient, private authSrv: AuthService, private router:Router,private route : ActivatedRoute) {
    

  }

  ngOnInit() :void{
    this.getProdotti();
   
    this.term = this.route.snapshot.paramMap.get('term') || ""

  }

  
  


 public getProdotti(): void {
  this.authSrv.getProdotti().subscribe(
    (response: Prodotti[])=>{
        console.log(response);
    this.prodotti = response;
    console.log(this.prodotti);
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
      }
  )
 }
 public getProdottiPag(): void {
    this.authSrv.getProdottiPag().subscribe(
      (response: Prodotti[])=>{
          console.log(response);
      this.prodotti = response;
      console.log(this.prodotti);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        }
    )
   }
 aggiungiCarrello(idP: number, idU: number, q:number){
this.authSrv.addCarrelloParam(idP, idU, q).subscribe(
  {
    next: data=>console.log(data),
    error: error=>console.log("aggiungiCarrello ", error),
    complete: ()=>console.log("aggiungiCarrello completato")
  }
)



 }
 aggiungiAiPreferiti(idP: number, idU: number){
  this.authSrv.addFavoritiParam(idP, idU).subscribe(
    {
      next: data=>{console.log(data),
        this.disabledButton = true;
      },
      error: error=>console.log("aggiungiAiPreferiti ", error),
      complete: ()=>console.log("aggiungiAiPreferiti completato")
    }
  )
 }
 /*getProdottoById(): void{
    this.http.get<Prodottiread>(`http://localhost:8080/api/prodotti/${this.idProdottospecifico}`)
    .subscribe(prodottiread=>this.prodottiread = prodottiread);
   }*/
  go(prodottiId: number){
    this.authSrv.getProdottoById(prodottiId).subscribe(prodotti=>{

        this.router.navigate(["prodotto", prodottiId])
    }
        )
  }
  
 }

