import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EmployeService } from '../services/employe.service';
import { MachineService } from '../services/machine.service';

@Component({
  selector: 'app-employes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './employes.html',
  styleUrl: './employes.css'
})
export class EmployesComponent implements OnInit {
  employes: any[] = [];
  machines: any[] = [];
  form: any = { nom: '', poste: '' };
  editId: number | null = null;
  showForm = false;

  constructor(private employeService: EmployeService, private machineService: MachineService, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.load();
    this.machineService.getAll().subscribe(m => { this.machines = m; this.cdr.detectChanges(); });
  }

  load() {
    this.employeService.getAll().subscribe(data => { this.employes = data; this.cdr.detectChanges(); });
  }

  openForm(e?: any) {
    this.showForm = true;
    if (e) {
      this.editId = e.id;
      this.form = { nom: e.nom, poste: e.poste };
    } else {
      this.editId = null;
      this.form = { nom: '', poste: '' };
    }
  }

  save() {
    const obs = this.editId
      ? this.employeService.update(this.editId, this.form)
      : this.employeService.create(this.form);
    obs.subscribe(() => { this.load(); this.showForm = false; });
  }

  delete(id: number) {
    if (confirm('Supprimer cet employé ?'))
      this.employeService.delete(id).subscribe(() => this.load());
  }

  assignMachine(empId: number, machineId: number) {
    this.employeService.assignMachine(empId, machineId).subscribe(() => this.load());
  }
}
