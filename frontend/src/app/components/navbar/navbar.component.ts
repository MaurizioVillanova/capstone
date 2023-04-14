import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Component, EventEmitter, OnInit, Output, SimpleChanges } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { NgbDropdownModule, NgbNavModule } from "@ng-bootstrap/ng-bootstrap";
import { ReplaySubject } from "rxjs/internal/ReplaySubject";
import { AuthResponse } from "src/app/interfaces/auth-response";
import { UserData } from "src/app/interfaces/user-data";
import { User } from "src/app/interfaces/user";

import { AuthService } from "src/app/services/auth.service";
import { Prodotti } from "src/app/interfaces/prodotti";

@Component({
    selector: "app-navbar",
    templateUrl: "./navbar.component.html",
    styleUrls: ["./navbar.component.scss"],
})
export class NavbarComponent implements OnInit {
    user!: User;
    searchTerm: string = '';
    prodotti: Prodotti[]=[];
    users: UserData[] = [];
    localStorage!: Storage;
    roles: any
    
   // risultatiRicerca: Prodotti[] = [];
    loggedSubj = new ReplaySubject<false | AuthResponse["user"]>();
    logged = false;


    constructor(
        private authSrv: AuthService,
        http: HttpClient,
        private router: Router,
        private route: ActivatedRoute
    ) {
        this.users = [];

        this.localStorage = window.localStorage;
    }
  
    ngOnInit(): void {
        this.roles = this.localStorage.getItem("roles");
        this.authSrv.mySubj.subscribe({
            next: logg=>{
                console.log('-------', logg);
                this.logged = true
            }
            
        })
       
        console.log(localStorage.getItem("token"));
        if (localStorage.getItem("token") != null) {
            this.logged = true;
        } else {
            this.logged = false;
        }
        this.authSrv.getUserById2();
           this.utente();
        
 
        
     
       /* console.log(localStorage.getItem("roles"));
        if (localStorage.getItem("ROLE_ADMIN") != null) {
            this.amministratore= true;
        } else {
            this.amministratore = false;
        }*/
       
        //this.getUser();
           const id = +this.route.snapshot.paramMap.get('id')!;
    this.authSrv.getUtenteById(id).subscribe({
        next: (user) => this.user = user,
        error: (error) => console.log(error),
        complete: () => console.log("ok", )
      }) 

//this.logout();
//this.go()
    }
    logout() {
        this.authSrv.logout();
       this.logged = false
    }
    
    go() {
        this.router.navigate(["user",this.localStorage.getItem('id')]);
        
    }
    /*navigateToUser() {
        console.log("ciao"); 
        this.router.navigate(['user', { user: JSON.stringify(this.authSrv.loggedUser) }]);
    }*/
 
    utente(){
        if(localStorage.getItem("token") != null){
        this.logged = true}
        else {
            this.logged = false
        }
    }
    
    
}



