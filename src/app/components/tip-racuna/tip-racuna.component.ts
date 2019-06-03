import { Component, OnInit, ViewChild } from '@angular/core';
import { TipRacuna } from 'src/app/models/tip-racuna';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { TipRacunaService } from 'src/app/services/tip-racuna.service';
import { MatDialog, MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { TipRacunaDialogComponent } from '../dialogs/tip-racuna-dialog/tip-racuna-dialog.component';

@Component({
  selector: 'app-tip-racuna',
  templateUrl: './tip-racuna.component.html',
  styleUrls: ['./tip-racuna.component.css']
})
export class TipRacunaComponent implements OnInit {

  displayedColumns = ['id', 'naziv', 'oznaka', 'opis', 'actions'];
  dataSource: MatTableDataSource<TipRacuna>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public httpClient: HttpClient, public tipRacunaService: TipRacunaService, public dialog: MatDialog ) { }

  ngOnInit() {
    this.loadData();
  }

  public loadData() {
    this.tipRacunaService.getAllTipRacuna().subscribe(data =>{
      this.dataSource = new MatTableDataSource(data);

      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  public openDialog(flag: number, id: number, naziv: string, oznaka: string, opis: string) {
    const dialogRef = this.dialog.open(TipRacunaDialogComponent, 
                                      {data: {id: id, naziv: naziv, oznaka: oznaka, opis: opis}}
    );

    dialogRef.componentInstance.flag = flag;

    dialogRef.afterClosed().subscribe(result =>{
      if (result == 1)
        this.loadData();
    })

  }

  applyFilter(filterValue: string){
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;
  }
}