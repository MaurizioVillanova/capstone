import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ProdottiComponent } from './components/prodotti/prodotti.component';
import { AuthService } from './services/auth.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FooterComponent } from './components/footer/footer.component';
import { OffcanvasComponent } from './components/offcanvas/offcanvas.component';
import { PosterComponent } from './components/poster/poster.component';
import { FunkoPopComponent } from './components/funko-pop/funko-pop.component';
import { OnePieceComponent } from './components/one-piece/one-piece.component';
import { DragonBall4Component } from './components/dragon-ball/dragon-ball4.component';
import { NarutoComponent } from './components/naruto/naruto.component';
import { BundleComponent } from './components/bundle/bundle.component';
import { ProfiloComponent } from './components/profilo/profilo.component';
import { CarrelloComponent } from './components/carrello/carrello.component';
import { ListaPreferitiComponent } from './components/lista-preferiti/lista-preferiti.component';
import { ProdottospecificoComponent } from './components/prodottospecifico/prodottospecifico.component';
import { UserComponent } from './components/user/user.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { PreferitoComponent } from './components/preferito/preferito.component';
import { AggiungiprodottoComponent } from './components/aggiungiprodotto/aggiungiprodotto.component';

import { NgxPayPalModule } from 'ngx-paypal';
import { ActionFigureComponent } from './components/action-figure/action-figure.component';
import { MarvelComponent } from './components/marvel/marvel.component';
import { StarWarsComponent } from './components/star-wars/star-wars.component';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    ProdottiComponent,
    FooterComponent,
    OffcanvasComponent,
    PosterComponent,
    FunkoPopComponent,
    OnePieceComponent,
    DragonBall4Component,
    NarutoComponent,
    BundleComponent,
    ProfiloComponent,
    CarrelloComponent,
    ListaPreferitiComponent,
    ProdottospecificoComponent,
    UserComponent,
    PreferitoComponent,
    AggiungiprodottoComponent,

    ActionFigureComponent,
    MarvelComponent,
    StarWarsComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    NgxPayPalModule

  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
