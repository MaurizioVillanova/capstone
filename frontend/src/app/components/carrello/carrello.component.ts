import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import { Carrello } from 'src/app/interfaces/carrello';
import { Carrelloread } from 'src/app/interfaces/carrelloread';
import { CarrelloRequest } from 'src/app/interfaces/CarrelloRequest';
import { Prodotti } from 'src/app/interfaces/prodotti';
import { User } from 'src/app/interfaces/user';
import { AuthService } from 'src/app/services/auth.service';
import { ICreateOrderRequest, IPayPalConfig, NgxPayPalModule } from 'ngx-paypal';
@Component({
  selector: 'app-carrello',
  templateUrl: './carrello.component.html',
  styleUrls: ['./carrello.component.scss']
})
export class CarrelloComponent implements OnInit {
    //userId!: number;
    //cartItems!: Carrelloread[];
    //carrello:any
   // user!:any;
   userId: any | undefined;
   carrelloId: any | undefined;
   carrelloRead: any| undefined;
   public payPalConfig?: IPayPalConfig;
    constructor(private authSrv: AuthService,private route : ActivatedRoute, private http: HttpClient) { }
    ngOnInit(): void {
        this.initConfig();
        const userId = localStorage.getItem('id');
        if (userId) {
          this.userId = parseInt(userId, 10);
          this.getCartItems(this.userId);
        }
    }
    private initConfig(): void {
        this.payPalConfig = {
        currency: 'EUR',
        clientId: 'sb',
        createOrderOnClient: (data) => <ICreateOrderRequest>{
          intent: 'CAPTURE',
          purchase_units: [
            {
              amount: {
                currency_code: 'EUR',
                value: this.getCartTotal().toString(),
                breakdown: {
                  item_total: {
                    currency_code: 'EUR',
                    value: this.getCartTotal().toString()
                  }
                }
              },
              items: [
                {
                  name: 'Enterprise Subscription',
                  quantity: '1',
                  category: 'DIGITAL_GOODS',
                  unit_amount: {
                    currency_code: 'EUR',
                    value: this.getCartTotal().toString()
                  },
                }
              ]
            }
          ]
        },
        advanced: {
          commit: 'true'
        },
        style: {
          label: 'paypal',
          layout: 'vertical'
        },
        onApprove: (data, actions) => {
          console.log('onApprove - transaction was approved, but not authorized', data, actions);
          actions.order.get().then((details: any) => {
            console.log('onApprove - you can get full order details inside onApprove: ', details);

          });
          this.svuota();
        },
        onClientAuthorization: (data) => {
          console.log('onClientAuthorization - you should probably inform your server about completed transaction at this point', data);
          this.svuota();
        //  this.showSuccess = true;
        },
        onCancel: (data, actions) => {
          console.log('OnCancel', data, actions);
        },
        onError: err => {
          console.log('OnError', err);
        },
        onClick: (data, actions) => {
          console.log('onClick', data, actions);
        
        },
      };
 
      }

       

getCartItems(userId: number): void {
    this.authSrv.getCartItems(userId).subscribe({
        next:  cartItems => {
            console.log(cartItems)
         //   if (Array.isArray(cartItems)) {
              this.carrelloRead = cartItems;
              console.log(this.carrelloRead)
           // } else {
            //  console.error('Invalid cart items data:', cartItems);
          //  }
          },
         
        error:e =>console.log(e),
        complete: ()=>console.log('getCartItems complete')
        });
  }
  rimuoviProdottoDalCarrello(userId: string, itemId: string){
    this.authSrv.deleteProdottoDalCarrello(userId, itemId).subscribe({
        next:  response => {
            console.log(response)
          },
         
        error:e =>console.log(e),
        complete: ()=>{
            console.log('rimuoviProdottodalCarrello complete');
            this.getCartItems(this.userId);
        }
        });
}
      public getCartTotal(): number {
  let total = 0;
  for (const item of this.carrelloRead.cartItems) {
    total += item.quantity * item.prodotto.prezzo;
  }
  return total;
}
verificaTotale(){
    console.log(this.getCartTotal());
    
}
svuota(){
    this.carrelloRead = {}
    this.carrelloRead.totalCost = 0
}
}