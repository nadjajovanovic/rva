import { Racun } from '../models/racun';
import { BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Injectable()
export class RacunService {
    private readonly API_URL = 'http://localhost:8083/racun/';
    private readonly API_URL_BYID = 'http://localhost:8083/racunZaKlijenta/';

    dataChange: BehaviorSubject<Racun[]> = new BehaviorSubject<Racun[]>([])

    constructor(private httpClient: HttpClient) { }

    public getRacunZaKlijenta(idKlijenta): Observable<Racun[]> {
        this.httpClient.get<Racun[]>(this.API_URL_BYID + idKlijenta).subscribe(data => {
          this.dataChange.next(data);
        },
        (error: HttpErrorResponse) => {
          console.log(error.name + ' ' + error.message);
        });
      return this.dataChange.asObservable();
    }

    public addRacun(racun: Racun): void {
        this.httpClient.post(this.API_URL, racun).subscribe();
      }
     
      public updateRacun(racun: Racun): void {
        this.httpClient.put(this.API_URL, racun).subscribe();
      }
     
      public deleteRacun(id: number): void {
        this.httpClient.delete(this.API_URL + id).subscribe();
      }
}