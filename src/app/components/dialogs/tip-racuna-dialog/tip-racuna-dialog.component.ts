import { Component, OnInit, Inject } from '@angular/core';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { TipRacunaService } from 'src/app/services/tip-racuna.service';
import { TipRacuna } from 'src/app/models/tip-racuna';

@Component({
  selector: 'app-tip-racuna-dialog',
  templateUrl: './tip-racuna-dialog.component.html',
  styleUrls: ['./tip-racuna-dialog.component.css']
})
export class TipRacunaDialogComponent implements OnInit {
  
  public flag: number;

  constructor(public snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<TipRacunaDialogComponent>,
              @Inject (MAT_DIALOG_DATA) public data: TipRacuna,
              public tipRacunaService: TipRacunaService) { }

  ngOnInit() {
  }

  public add(): void {
    this.tipRacunaService.addTipRacuna(this.data);
    this.snackBar.open("Uspešno dodat tip racuna: " + this.data.naziv, "U redu",{
      duration: 2500
    })
  }

  public update(): void{
    this.tipRacunaService.editTipRacuna(this.data);
    this.snackBar.open("Uspešno modifikovan tip racuna: " + this.data.naziv, "U redu",{
      duration: 1500
    })
  }

  public delete(): void{
    console.log("Brisem Kredit sa id: "+this.data.id);
    this.tipRacunaService.deleteTipRacuna(this.data.id);
    this.snackBar.open("Uspešno obrisan tip racuna: " + this.data.naziv, "U redu",{
      duration: 1500
    })
  }

  public cancel():void{
    this.dialogRef.close();
    this.snackBar.open("Odustali ste", "U redu")
  }

}
