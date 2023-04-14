import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/interfaces/user';
import {Router} from "@angular/router";
import { AuthService } from 'src/app/services/auth.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import jwt_decode from 'jwt-decode';
import { UserData } from 'src/app/interfaces/user-data';
import { ReplaySubject } from 'rxjs/internal/ReplaySubject';
import { AuthResponse } from 'src/app/interfaces/auth-response';
import { Preferito } from 'src/app/interfaces/preferito';

@Component({
  selector: 'app-profilo',
  templateUrl: './profilo.component.html',
  styleUrls: ['./profilo.component.scss']
})

export class ProfiloComponent implements OnInit {
 //profileUser: UserData[]  | any;
 //profilo: UserData[];
 users: User[];
 favoriti: Preferito[];
//informazioni! : User[]

logged = false
constructor(private http: HttpClient, private authSrv: AuthService, private router:Router ) {
  //this.profilo=[];
  this.users=[];
  this.favoriti=[];
}




  ngOnInit(): void {
    //this.getUserByUsername();
   this.authSrv.findall().subscribe({//3 callback rappresentano l'oggetto Observer in formato Json
    next: data=>this.users = data,
    error: errore=> console.log(errore),
    complete:()=>console.log("find all completato")



  })

   /* this.authSrv.user$.subscribe(
      (profile: any) => (this.profileUser = JSON.stringify(profile.user, null, 2)),
    );
    this.profilo = JSON.parse(this.profileUser || '{}');*/


    if(!window.localStorage.getItem('token')) {
      let token = localStorage.getItem('token');
    // this.getDecodedAccessToken(token);
      this.router.navigate(['login']);
      return;


  }
  console.log(localStorage.getItem("token"))
  if(localStorage.getItem("token")!=null){
    this.logged = true
  } else {
    this.logged = false
  }


  }
logOut() {
  localStorage.removeItem('token')
  this.router.navigateByUrl('/login').then(() => window.location.reload())
}

/*getDecodedAccessToken(token: string| null): any {

 if(token !==null){try {
  console.log(jwt_decode(token))
    return jwt_decode(token);
  } catch(Error) {
    return null;

  }if(token==null){
    console.log('tokennull')
  }

}
}*/

/*getUserById(id:number, token:string) {
  this.authSrv.getUserById*/

}
/*public getUserByEmail():void{
  this.authSrv.getUserEmail().subscribe(
    (response: UserData[])=>{
      this.profilo = response
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
      }
  )
{

}
}*/
/*public getUserByUsername(): void {
  this.authSrv.getUserByUsername().subscribe(
    (response: UserData[])=>{

      this.profilo = response;
      console.log(this.profilo)
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
      }
  )

}*/



