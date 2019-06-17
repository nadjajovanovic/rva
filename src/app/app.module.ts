import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { KreditComponent } from './components/kredit/kredit.component';
import { KlijentComponent } from './components/klijent/klijent.component';
import { TipRacunaComponent } from './components/tip-racuna/tip-racuna.component';
import { RacunComponent } from './components/racun/racun.component';
import { MatButtonModule, MatIconModule, MatListModule, MatGridListModule, MatSidenavModule, MatExpansionModule, MatTableModule, MatToolbarModule, MatSelectModule, MatDialogModule, MatInputModule, MatSnackBarModule, MatSortModule, MatPaginatorModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HelpComponent } from './components/core/help/help.component';
import { HomeComponent } from './components/core/home/home.component';
import { AuthorComponent } from './components/core/author/author.component';
import { HttpClientModule } from '@angular/common/http';
import { KreditService } from './services/kredit.service';
import { KreditDialogComponent } from './components/dialogs/kredit-dialog/kredit-dialog.component';
import { KlijentDialogComponent } from './components/dialogs/klijent-dialog/klijent-dialog.component';
import { RacunDialogComponent } from './components/dialogs/racun-dialog/racun-dialog.component';
import { TipRacunaDialogComponent } from './components/dialogs/tip-racuna-dialog/tip-racuna-dialog.component';
import { FormsModule } from '@angular/forms';
import { TipRacunaService } from './services/tip-racuna.service';
import { KlijentService } from './services/klijent.service';
import { RacunService } from './services/racun.service';

@NgModule({
  declarations: [
    AppComponent,
    KreditComponent,
    KlijentComponent,
    TipRacunaComponent,
    RacunComponent,
    HelpComponent,
    HomeComponent,
    AuthorComponent,
    KreditDialogComponent,
    KlijentDialogComponent,
    RacunDialogComponent,
    TipRacunaDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatSidenavModule,
    MatExpansionModule,
    MatTableModule,
    MatToolbarModule,
    MatSelectModule,
    HttpClientModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
    MatSnackBarModule,
    MatPaginatorModule, 
    MatSortModule
  ],
  entryComponents: [KreditDialogComponent, TipRacunaDialogComponent, KlijentDialogComponent, RacunDialogComponent],
  providers: [KreditService, TipRacunaService, KlijentService, RacunService],
  bootstrap: [AppComponent]
})
export class AppModule { }
