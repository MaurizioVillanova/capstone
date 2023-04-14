import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbOffcanvas } from '@ng-bootstrap/ng-bootstrap';
import { Carrello } from 'src/app/interfaces/carrello';
import { Preferito } from 'src/app/interfaces/preferito';
import { User } from 'src/app/interfaces/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-preferito',
  templateUrl: './preferito.component.html',
  styleUrls: ['./preferito.component.scss']
})
export class PreferitoComponent implements OnInit {
    public favoriti!: Preferito[];
    localStorage!: Storage;
    carrello!: Carrello[];
    user!:User[];
  constructor(private offcanvasService: NgbOffcanvas,private http: HttpClient, private authSrv: AuthService, private router:Router,private route : ActivatedRoute) { 
    this.favoriti=[];
    this.localStorage = window.localStorage;
  }

  ngOnInit(): void {
    this.getPreferiti();
  }
  public  getPreferiti(): void {
    this.authSrv.getFavoriti().subscribe(
      (response : Preferito[])=>{
      this.favoriti = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
      }
      )
  }
  elimina(favoritoId:number, index: number){
    this.authSrv.deleteFavoriti(favoritoId).subscribe((res)=>{
      this.favoriti?.splice(index, 1)
    })
  }
}
