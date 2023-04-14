import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { Prodotti } from 'src/app/interfaces/prodotti';
import { AuthService } from 'src/app/services/auth.service';

import { LoginRequest } from '../../interfaces/auth-response';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form!:FormGroup;
  public prodotti : Prodotti[];
  public editProdotto!: Prodotti;
  public deleteProdotto!: Prodotti;

  constructor( private formBuilder: FormBuilder, private authSrv:AuthService, private r:Router) {
this.prodotti = [];
  }
  mySubjectNext(){
    this.authSrv.mySubj.next('myLogged')
  }
  ngOnInit(): void {

    this.form = this.formBuilder.group({
      username: [],
      password: []
    })

   // console.log(this.getFormControl("email"));

  }
  public getProdotti(): void {
    this.authSrv.getProdotti().subscribe(
    (response: Prodotti[]) => {
    this.prodotti = response;
    console.log(this.prodotti)
    },
    (error: HttpErrorResponse) => {
    alert(error.message);
    }
    );
    }
    /*public onAddDipendente(addForm: NgForm): void {
      document.getElementById('add-dipendente-form') .click();
      this.dipendenteService.addDipendente(addForm.value).subscribe(
        (response: Dipendente) => {
          console.log(response);
          this.getDipendenti();
          addForm.reset();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
          addForm.reset();
        }
      );
    }

    public onUpdateProdotto(prodotto: Prodotti): void {
      this.authSrv.updateProdotto(prodotto).subscribe(
        (response: Prodotti) => {
          console.log(response);
          this.getProdotti();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }

    public onDeleteProdotto(prodottoId: number): void {
      this.authSrv.deleteProdotto(prodottoId).subscribe(
        (response: void) => {
          console.log(response);
          this.getProdotti();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }

    public searchProdotti(key: string): void {
      console.log(key);
      const results: Prodotti[] = [];
      for (const prodotto of this.prodotti) {
        if (prodotto.nome.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || prodotto.descrizione.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
          results.push(prodotto);
        }
      }
      this.prodotti = results;
      if (results.length === 0 || !key) {
        this.getProdotti();
      }
    }

    public onOpenModal(prodotto : Prodotti, mode: string): void {
      const container = document.getElementById('main-container');
      const button = document.createElement('button');
      button.type = 'button';
      button.style.display = 'none';
      button.setAttribute('data-toggle', 'modal');
      if (mode === 'add') {
        button.setAttribute('data-target', '#addDipendenteModal');
      }
      if (mode === 'edit') {
        this.editProdotto = prodotto;
        button.setAttribute('data-target', '#updateDipendenteModal');
      }
      if (mode === 'delete') {
        this.deleteProdotto =prodotto;
        button.setAttribute('data-target', '#deleteEmployeeModal');
      }

    }*/

  login() {
    if(this.form.valid) {
      let data :LoginRequest = {
        username: this.getFormControl('username').value,
        password: this.getFormControl('password').value
      }
this.mySubjectNext();
      console.log(data);
      this.authSrv.login(data).subscribe(res=>{
        this.r.navigate(['/'])
      })
    }
  }

  getFormControl(n:string){
    return this.form.get(n) as FormControl
  }
}


