import { Component, OnInit, Inject } from '@angular/core';
import { TipRacuna } from 'src/app/models/tip-racuna';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Racun } from 'src/app/models/racun';
import { RacunService } from 'src/app/services/racun.service';
import { TipRacunaService } from 'src/app/services/tip-racuna.service';

@Component({
  selector: 'app-racun-dialog',
  templateUrl: './racun-dialog.component.html',
  styleUrls: ['./racun-dialog.component.css']
})
export class RacunDialogComponent implements OnInit {

  tipRacuni: TipRacuna[];
  public flag: number;

 constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<RacunDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Racun,
    public racunService: RacunService,
    public tipRacunaService: TipRacunaService
  ) { }

  ngOnInit() {
    this.tipRacunaService.getAllTipRacuna().subscribe(tipRacuni =>
      this.tipRacuni = tipRacuni
      );
  }

  public add(): void {
    this.data.id = -1;
    this.racunService.addRacun(this.data);
    this.snackBar.open("Uspešno dodat racun", "U redu", { duration: 2500 });
  }

  public update(): void {
    this.racunService.updateRacun(this.data);
    this.snackBar.open("Uspešno modifikovan racun", "U redu", { duration: 2500 });
  }

  public delete(): void {
    this.racunService.deleteRacun(this.data.id);
    this.snackBar.open("Uspešno obrisan racun", "U redu", { duration: 2000 });
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open("Odustali ste", "U redu", { duration: 1000 });
  }

  compareTo(a, b) {
    return a.id == b.id;
  }

}
