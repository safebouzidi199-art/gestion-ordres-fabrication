import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class MachineService {
  private api = 'http://localhost:8080/machines';
  constructor(private http: HttpClient) {}

  getAll() { return this.http.get<any[]>(this.api); }
  getById(id: number) { return this.http.get<any>(`${this.api}/${id}`); }
  create(m: any) { return this.http.post<any>(this.api, m); }
  update(id: number, m: any) { return this.http.put<any>(`${this.api}/${id}`, m); }
  delete(id: number) { return this.http.delete(`${this.api}/${id}`); }
  getMachinesEnPanne() { return this.http.get<any[]>(`${this.api}/panne`); }
}
