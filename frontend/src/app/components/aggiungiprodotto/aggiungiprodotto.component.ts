import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';  

import { Prodotto } from 'src/app/interfaces/Prodotto';

@Component({
  selector: 'app-aggiungiprodotto',
  templateUrl: './aggiungiprodotto.component.html',
  styleUrls: ['./aggiungiprodotto.component.scss']
})
export class AggiungiprodottoComponent implements OnInit {
  prodotto: Prodotto = {
    nome: '',
    categoria: '',
    descrizione: '',
    disponibilita: '',
    prezzo: 0,
    immagineUrl: ''
  };
  @ViewChild('prodottoForm') prodottoForm!: NgForm; // accede al form tramite la direttiva ViewChild

  constructor(private authSrv: AuthService) { }

  onSubmit(): void {
    console.log(this.prodotto);
    this.authSrv.addProdotti(this.prodotto)
      .subscribe({
        next: prodotto => {
          console.log('Prodotto creato:', prodotto);
          this.prodottoForm.reset(); // resetta il form
        },
        error: error => console.error('Errore nella creazione del prodotto:', error),
        complete: () => console.log("fatto")
      });
  }

  ngOnInit(): void { }

}