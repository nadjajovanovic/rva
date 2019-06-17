import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { KlijentComponent } from './components/klijent/klijent.component';
import { KreditComponent } from './components/kredit/kredit.component';
import { TipRacunaComponent } from './components/tip-racuna/tip-racuna.component';
import { RacunComponent } from './components/racun/racun.component';
import { AuthorComponent } from './components/core/author/author.component';
import { HelpComponent } from './components/core/help/help.component';
import { HomeComponent } from './components/core/home/home.component';

const routes: Routes = [
  { path: 'klijent', component: KlijentComponent },
  { path: 'kredit', component: KreditComponent },
  { path: 'tip-racuna', component: TipRacunaComponent },
  { path: 'author', component: AuthorComponent },
  { path: 'help', component: HelpComponent },
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
