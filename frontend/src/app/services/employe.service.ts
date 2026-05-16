import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class EmployeService {
  private api = `${environment.apiUrl}/employes`;
  constructor(private http: HttpClient) {}

  getAll() { return this.http.get<any[]>(this.api); }
  getById(id: number) { return this.http.get<any>(`${this.api}/${id}`); }
  create(e: any) { return this.http.post<any>(this.api, e); }
  update(id: number, e: any) { return this.http.put<any>(`${this.api}/${id}`, e); }
  delete(id: number) { return this.http.delete(`${this.api}/${id}`); }
  assignMachine(empId: number, machineId: number) {
    return this.http.put<any>(`${this.api}/${empId}/machine/${machineId}`, {});
  }
}
