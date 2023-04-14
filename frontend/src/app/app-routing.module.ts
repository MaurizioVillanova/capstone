import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components//register/register.component';
import { PosterComponent } from './components/poster/poster.component';
import { FunkoPopComponent } from './components/funko-pop/funko-pop.component';
import { DragonBall4Component } from './components/dragon-ball/dragon-ball4.component';
import { ActionFigureComponent } from './components/action-figure/action-figure.component';
import { BundleComponent } from './components/bundle/bundle.component';
import { NarutoComponent } from './components/naruto/naruto.component';
import { MarvelComponent } from './components/marvel/marvel.component';
import { StarWarsComponent } from './components/star-wars/star-wars.component';
import { OnePieceComponent } from './components/one-piece/one-piece.component';
import { ProfiloComponent } from './components/profilo/profilo.component';
import { ListaPreferitiComponent } from './components/lista-preferiti/lista-preferiti.component';
import { CarrelloComponent } from './components/carrello/carrello.component';
import { ProdottospecificoComponent } from './components/prodottospecifico/prodottospecifico.component';
import { UserComponent } from './components/user/user.component';
import { PreferitoComponent } from './components/preferito/preferito.component';
import { AggiungiprodottoComponent } from './components/aggiungiprodotto/aggiungiprodotto.component';

const routes: Routes = [
  {path:"", component: HomeComponent},
  {path:"login", component: LoginComponent},
  { path: "register",component: RegisterComponent},
  { path: "poster",component: PosterComponent},
  { path: "Funko-pop",component: FunkoPopComponent},
  { path: "Dragon-Ball",component: DragonBall4Component},
  { path: "One-piece",component: OnePieceComponent},
  { path: "Action-Figure",component: ActionFigureComponent},
  { path: "Bundle",component: BundleComponent},
  { path: "Naruto",component: NarutoComponent},
  { path: "Marvel",component: MarvelComponent},
  { path: "Star-Wars",component: StarWarsComponent},
  { path: "profilo",component: ProfiloComponent},
  { path: "lista_preferiti/:id",component: ListaPreferitiComponent},
  { path: "carrello/:id",component: CarrelloComponent},
  {path: "prodotto/:id", component: ProdottospecificoComponent},
  {path: "user/:id ", component: UserComponent},
 {path: "preferito", component: PreferitoComponent},
 {path: "aggiungiprodotto", component: AggiungiprodottoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
