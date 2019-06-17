import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { Racun } from 'src/app/models/racun';
import { HttpClient } from '@angular/common/http';
import { RacunService } from 'src/app/services/racun.service';
import { MatDialog, MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Klijent } from 'src/app/models/klijent';
import { TipRacuna } from 'src/app/models/tip-racuna';
import { RacunDialogComponent } from '../dialogs/racun-dialog/racun-dialog.component';

@Component({
  selector: 'app-racun',
  templateUrl: './racun.component.html',
  styleUrls: ['./racun.component.css']
})
export class RacunComponent implements OnInit {

  displayedColumns = ['id', 'naziv', 'opis', 'oznaka', 'klijent', 'tipRacuna', 'actions'];
  dataSource: MatTableDataSource<Racun>;

  @Input() selektovanKlijent: Klijent;
  
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public racunService: RacunService, public dialog: MatDialog) { }
  
  ngOnInit() {

  }
  
  ngOnChanges() {
   if (this.selektovanKlijent.id) {
     this.loadData();
   }
  }

  public loadData() {
    this.racunService.getRacunZaKlijenta(this.selektovanKlijent.id).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);

      this.dataSource.filterPredicate = (data, filter: string) => {
        const accumulator = (currentTerm, key) => {
          return key === 'tipRacunaBean' ? currentTerm + data.tipRacunaBean.naziv : currentTerm + data[key];
        };
        const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
        const transformedFilter = filter.trim().toLowerCase();
        return dataStr.indexOf(transformedFilter) !== -1;
      };

      this.dataSource.sortingDataAccessor = (data, property) => {
        switch(property) {
          case 'tipRacunaBean': return data.tipRacunaBean.naziv.toLocaleLowerCase();
          default: return data[property];
        }
      };

      this.dataSource.paginator = this.paginator;
     this.dataSource.sort = this.sort;
    });
  }

  public openDialog(flag: number, id: number, naziv: string, opis: string, oznaka: string, klijent: Klijent, tipRacuna: TipRacuna) {
    const dialogRef = this.dialog.open(RacunDialogComponent, {
      data: { id: id, naziv: naziv, opis: opis, oznaka: oznaka, klijentBean: klijent, tipRacunaBean: tipRacuna}
    });
    dialogRef.componentInstance.flag = flag;
    if (flag == 1)
      dialogRef.componentInstance.data.klijentBean = this.selektovanKlijent;
 
    dialogRef.afterClosed().subscribe(result => {
      if (result == 1)
        this.loadData();
    });
  }

  applyFilter(filterValue: string){
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;
  }


}
