import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


import { User } from 'src/app/interfaces/user';
import { AuthService } from 'src/app/services/auth.service';
import { preferitiItems } from 'src/app/interfaces/preferitiItems';
import { Prodotti } from 'src/app/interfaces/prodotti';
import { Preferito } from 'src/app/interfaces/preferito';
@Component({
  selector: 'app-lista-preferiti',
  templateUrl: './lista-preferiti.component.html',
  styleUrls: ['./lista-preferiti.component.scss']
})

export class ListaPreferitiComponent implements OnInit {
    userId: any | undefined;
  // preferito !: Preferito;
  //favoriti: any | undefined;
  preferitiItems : any| undefined;
  user!: User;
  logged = false
preferiti!: any[];
obiObjects: any = {
    16: { nome : 'iPhone', prezzo: 20.99 },
    17: { nome : 'iMac', prezzo: 19.99},
    18: { nome : 'iPad', prezzo: 18.99 }
}
objArray!: any[];
Preferito : any= {}
  constructor(private http: HttpClient, private authSrv: AuthService,  private router:Router ) {
    //this.favoriti = [];
    
   
   }

  ngOnInit(): void {
    this.objArray = Object.entries(this.obiObjects);
    this.preferiti = Object.entries(this.Preferito);
    const userId = localStorage.getItem('id');
    if (userId) {
      this.userId = parseInt(userId, 10);
      this.getPreferitiItems(this.userId);
    }
   /* const userId = localStorage.getItem('id');S
        if (userId) {
          this.userId = parseInt(userId);
          this.getCartItems(this.userId);
        }
    }*/
   // this.getPreferiti();
    /*if(!window.localStorage.getItem('token')) {
      let token = localStorage.getItem('token');
    // this.getDecodedAccessToken(token);
      this.router.navigate(['login']);
      return;*/
      
  }
  getPreferitiItems(userId: number): void {
    this.authSrv.getPreferitiItems(userId).subscribe((preferiti: any[]) => {
      this.preferiti = preferiti;
      console.log(this.preferiti)
     
    });
  }
      cancella(id: any) {
        alert(id);
    }

  /*rimuoviProdottoDaiPreferiti(userId: string, itemId: string){
    this.authSrv.deleteProdottoDaiPreferiti(userId,  itemId).subscribe({
        next:  response => {
            console.log(response)
          },
         
        error:e =>console.log(e),
        complete: ()=>{
            console.log('rimuoviProdottodalCarrello complete');
            this.getPreferitiItems(this.userId);
        }
        });
}*/
rimuoviProdottoDaiPreferiti(userId: string, idPreferito: string){
    this.authSrv.deleteProdottoDaiPreferiti(userId,  idPreferito).subscribe({
      next: response => {
        console.log(response)
      },
      error: e => console.log(e),
      complete: () => {
        console.log('rimuoviProdottodalCarrello complete');
        this.getPreferitiItems(this.userId);
      }
    });
  }
   

}
  
