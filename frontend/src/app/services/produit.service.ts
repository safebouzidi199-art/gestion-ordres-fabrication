import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ProduitService {
  private api = 'http://localhost:8080/produits';
  constructor(private http: HttpClient) {}

  getAll() { return this.http.get<any[]>(this.api); }
  getById(id: number) { return this.http.get<any>(`${this.api}/${id}`); }
  create(p: any) { return this.http.post<any>(this.api, p); }
  update(id: number, p: any) { return this.http.put<any>(`${this.api}/${id}`, p); }
  delete(id: number) { return this.http.delete(`${this.api}/${id}`); }
}
