import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { TipRacuna } from '../models/tip-racuna';

@Injectable()
export class TipRacunaService {

    private readonly API_URL = 'http://localhost:8083/tip-racuna/';

    dataChange: BehaviorSubject<TipRacuna[]> = new BehaviorSubject<TipRacuna[]>([])

    constructor(private httpClient: HttpClient) { }

    public getAllTipRacuna(): Observable<TipRacuna[]> {
        this.httpClient.get<TipRacuna[]>(this.API_URL).subscribe(data => {
            this.dataChange.next(data);
        })
        return this.dataChange.asObservable();
    }

    public addTipRacuna(tipRacuna: TipRacuna): void {
        this.httpClient.post(this.API_URL, tipRacuna).subscribe();
    }

    public editTipRacuna(tipRacuna: TipRacuna): void {
        this.httpClient.put(this.API_URL, tipRacuna).subscribe();
    }

    public deleteTipRacuna(id: number): void {
        this.httpClient.delete(this.API_URL + id).subscribe();
    }

}