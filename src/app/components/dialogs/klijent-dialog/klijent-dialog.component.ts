import { Component, OnInit, Inject } from '@angular/core';
import { Kredit } from 'src/app/models/kredit';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Klijent } from 'src/app/models/klijent';
import { KlijentService } from 'src/app/services/klijent.service';
import { KreditService } from 'src/app/services/kredit.service';

@Component({
  selector: 'app-klijent-dialog',
  templateUrl: './klijent-dialog.component.html',
  styleUrls: ['./klijent-dialog.component.css']
})
export class KlijentDialogComponent implements OnInit {

  krediti: Kredit[];

  public flag: Number;

  constructor(public snackBar: MatSnackBar,
   public dialogRef: MatDialogRef<KlijentDialogComponent>,
   @Inject(MAT_DIALOG_DATA) public data: Klijent,
   public klijentService: KlijentService,
   public kreditService: KreditService
   ) { }

  ngOnInit() {
    this.kreditService.getAllKredite().subscribe(krediti =>
      this.krediti = krediti
    );
  }

  compareTo(a, b) {
    return a.id == b.id;
  }

  public add(): void {
    this.data.id = -1;
    this.klijentService.addKlijent(this.data);
    this.snackBar.open("Uspešno dodat klijent", "U redu", { duration: 2500 });
  }
 
  public update(): void {
    this.klijentService.editKlijent(this.data);
    this.snackBar.open("Uspešno modifikovan klijent", "U redu", { duration: 2500 });
  }
 
  public delete(): void {
    this.klijentService.deleteKlijent(this.data.id);
    this.snackBar.open("Uspešno obrisan klijent", "U redu", { duration: 2000 });
  }
 
  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open("Odustali ste", "U redu", { duration: 1000 });
  }

}
