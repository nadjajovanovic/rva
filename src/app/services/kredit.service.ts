import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from 'rxjs';
import { Kredit } from '../models/kredit';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class KreditService {
    private readonly API_URL = 'http://localhost:8083/kredit/';

    dataChange: BehaviorSubject<Kredit[]> = new BehaviorSubject<Kredit[]>([]);

    constructor(private httpClient: HttpClient) { }

    public getAllKredite(): Observable<Kredit[]> {
        this.httpClient.get<Kredit[]>(this.API_URL).subscribe(data => {
            this.dataChange.next(data);
        })
        return this.dataChange.asObservable();
    }

    public addKredit(kredit: Kredit): void {
        this.httpClient.post(this.API_URL, kredit).subscribe();
    }

    public editKredit(kredit: Kredit): void {
        this.httpClient.put(this.API_URL, kredit).subscribe();
    }

    public deleteKredit(id: number): void {
        this.httpClient.delete(this.API_URL + id).subscribe();
    }


}