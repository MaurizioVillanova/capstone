import { Component, OnInit } from '@angular/core';
import { Prodotti } from 'src/app/interfaces/prodotti';
import { AuthService } from 'src/app/services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
@Component({
  selector: 'app-action-figure',
  templateUrl: './action-figure.component.html',
  styleUrls: ['./action-figure.component.scss']
})
export class ActionFigureComponent implements OnInit {
    p: number = 0;
    size: number = 10;
    totalPages!: number;
  public prodotti: Prodotti[];
  constructor(private authSrv: AuthService) {
     this.prodotti = [];
  }

  ngOnInit(): void {
    this.getProdottiByCategoria();
  }

  public getProdottiByCategoria(): void {
    this.authSrv.getProdottiByCategoriaFigure().subscribe(
      (response: Prodotti[])=>{
      this.prodotti = response;
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
        next: data=>console.log(data),
        error: error=>console.log("aggiungiAiPreferiti ", error),
        complete: ()=>console.log("aggiungiAiPreferiti completato")
      }
    )
   }
}