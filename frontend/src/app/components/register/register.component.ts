import { Component, OnInit } from '@angular/core';
import { FormBuilder, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError } from 'rxjs';
import { RegisterRequest } from '../../interfaces/auth-response';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  err:string|undefined;

  constructor(private authSrv:AuthService, private r:Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {

  }

  register(f:NgForm):void {
    if(f.valid) {
      let data :RegisterRequest = {
        email: f.value.email,
        password: f.value.password,
        nomeCompleto: f.value.nomeCompleto,
        username: f.value.username


      }
      console.log(data);
      this.authSrv.register(data).pipe(catchError(err=>{
        this.err = err.error
        throw err
      })).subscribe(res=>{
        console.log(res);
        this.err = undefined
        //TODO: rotta a login con parametro emial per autocompletamento
        this.r.navigate(["login"]);
      })
    }
  }

}
