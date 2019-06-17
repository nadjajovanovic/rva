import { Component, OnInit, Inject } from '@angular/core';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Kredit } from 'src/app/models/kredit';
import { KreditService } from 'src/app/services/kredit.service';

@Component({
  selector: 'app-kredit-dialog',
  templateUrl: './kredit-dialog.component.html',
  styleUrls: ['./kredit-dialog.component.css']
})
export class KreditDialogComponent implements OnInit {

  public flag: number;

  constructor(public snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<KreditDialogComponent>,
              @Inject (MAT_DIALOG_DATA) public data: Kredit,
              public kreditService: KreditService) { }

  ngOnInit() {
  }

  public add(): void {
    this.kreditService.addKredit(this.data);
    this.snackBar.open("Uspešno dodat kredit: " + this.data.naziv, "U redu",{
      duration: 2500
    })
  }

  public update(): void{
    this.kreditService.editKredit(this.data);
    this.snackBar.open("Uspešno modifikovan kredit: " + this.data.naziv, "U redu",{
      duration: 1500
    })
  }

  public delete(): void{
    console.log("Brisem Kredit sa id: "+this.data.id);
    this.kreditService.deleteKredit(this.data.id);
    this.snackBar.open("Uspešno obrisan kredit: " + this.data.naziv, "U redu",{
      duration: 1500
    })
  }

  public cancel():void{
    this.dialogRef.close();
    this.snackBar.open("Odustali ste", "U redu")
  }

}
