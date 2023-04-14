import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/interfaces/user';
import { AuthService } from 'src/app/services/auth.service';
import {Character} from 'src/app/interfaces/Character';
import {Axios} from 'axios';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
user!:User
public randomCharacter!: Character;
    
  constructor(private authSrv: AuthService,private route : ActivatedRoute, private http: HttpClient) { }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.authSrv.getUtenteById(id).subscribe({
        next: (user) => this.user = user,
        error: (error) => console.log(error),
        complete: () => console.log("ok")
      }) 
      this.http.get<any>('https://kitsu.io/api/edge/characters?page[limit]=20').subscribe((data) => {
      const characters = data.data;
      this.randomCharacter = characters[Math.floor(Math.random() * characters.length)];
    });
  }
  
}

