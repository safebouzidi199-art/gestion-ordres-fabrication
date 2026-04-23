import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class OrdreService {
  private api = 'http://localhost:8080/ordres';
  constructor(private http: HttpClient) {}

  getAll() { return this.http.get<any[]>(this.api); }
  getById(id: number) { return this.http.get<any>(`${this.api}/${id}`); }
  create(o: any) { return this.http.post<any>(this.api, o); }
  updateEtat(id: number, etat: string) {
    return this.http.put<any>(`${this.api}/${id}/etat?etat=${etat}`, {});
  }
  delete(id: number) { return this.http.delete(`${this.api}/${id}`); }
}
