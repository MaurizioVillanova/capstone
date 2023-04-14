import { Component, OnInit } from '@angular/core';
import { NgbDatepickerModule, NgbOffcanvas, OffcanvasDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth.service';
import { Preferito } from 'src/app/interfaces/preferito';
import { Carrello } from 'src/app/interfaces/carrello';
import { User } from 'src/app/interfaces/user';
import { Cart } from 'src/app/interfaces/Cart';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-offcanvas',


  templateUrl: './offcanvas.component.html',
  styleUrls: ['./offcanvas.component.scss']
})
export class OffcanvasComponent implements OnInit {
	closeResult = '';
    localStorage!: Storage;
   public favoriti: Preferito[];
   cart!: Cart[];
    carrello!: Carrello[];
    user!:User[];
    roles: any
  constructor(private offcanvasService: NgbOffcanvas,private http: HttpClient, private authSrv: AuthService, private router:Router,private route : ActivatedRoute ) {
    this.favoriti=[];
    this.localStorage = window.localStorage; // Inizializza la proprietà 'localStorage' con l'oggetto localStorage globale
  }
	open(content: any) {

		this.offcanvasService.open(content, { ariaLabelledBy: 'offcanvas-basic-title' }).result.then(
			(result) => {
				this.closeResult = `Closed with: ${result}`;
			},
			(reason) => {
				this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
			},
		);
	}

  ngOnInit(): void {
    
    this.roles = this.localStorage.getItem("roles");
    this.getPreferiti();
   // this.authSrv.
    //this.getUserByUsername();
   


  }
  private getDismissReason(reason: any): string {
		if (reason === OffcanvasDismissReasons.ESC) {
			return 'by pressing ESC';
		} else if (reason === OffcanvasDismissReasons.BACKDROP_CLICK) {
			return 'by clicking on the backdrop';
		} else {
			return `with: ${reason}`;
		}
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

