import { Component, OnInit, ViewChild } from '@angular/core';
import { Klijent } from 'src/app/models/klijent';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { KlijentService } from 'src/app/services/klijent.service';
import { MatDialog, MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { Kredit } from 'src/app/models/kredit';
import { KlijentDialogComponent } from '../dialogs/klijent-dialog/klijent-dialog.component';

@Component({
  selector: 'app-klijent',
  templateUrl: './klijent.component.html',
  styleUrls: ['./klijent.component.css']
})
export class KlijentComponent implements OnInit {

  displayedColumns = ['id', 'ime', 'prezime', 'brojLk', 'kredit', 'actions'];
  dataSource: MatTableDataSource<Klijent>;
  selektovanKlijent: Klijent;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public httpClient: HttpClient, public klijentService: KlijentService, public dialog: MatDialog) { }

  ngOnInit() {
    this.loadData();
  }

  public loadData() {
    this.klijentService.getAllKlijente().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
  
      //pretraga po nazivu ugnježdenog objekta
      this.dataSource.filterPredicate = (data, filter: string) => {
        const accumulator = (currentTerm, key) => {
          return key === 'kreditBean' ? currentTerm + data.kreditBean.naziv : currentTerm + data[key];
        };
        const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
        const transformedFilter = filter.trim().toLowerCase();
        return dataStr.indexOf(transformedFilter) !== -1;
      };
  
       //sortiranje po nazivu ugnježdenog objekta
       this.dataSource.sortingDataAccessor = (data, property) => {
        switch(property) {
          case 'kreditBean': return data.kreditBean.naziv.toLocaleLowerCase();
          default: return data[property];
        }
      };
     
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  public openDialog(flag: number, id: number, ime: string, prezime: string, brojLk: number, kredit: Kredit ) {
    const dialogRef = this.dialog.open(KlijentDialogComponent, 
                                      { data: { id: id, ime: ime, prezime: prezime, brojLk: brojLk, kreditBean: kredit  } });
    dialogRef.componentInstance.flag = flag;
 
    dialogRef.afterClosed().subscribe(result => {
      if (result == 1){ 
         this.loadData();
      }
        
    });
  }

  selectRow(row){
    this.selektovanKlijent = row;
   }
  
   applyFilter(filterValue: string){
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;
  }
 

}
