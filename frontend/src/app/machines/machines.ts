import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MachineService } from '../services/machine.service';

@Component({
  selector: 'app-machines',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './machines.html',
  styleUrl: './machines.css'
})
export class MachinesComponent implements OnInit {
  machines: any[] = [];
  form: any = { nom: '', etat: 'DISPONIBLE', derniereMaintenance: '' };
  editId: number | null = null;
  showForm = false;

  etats = ['DISPONIBLE', 'EN_PANNE', 'EN_MAINTENANCE'];

  constructor(private machineService: MachineService, private cdr: ChangeDetectorRef) {}

  ngOnInit() { 
    this.load(); 
  }

  load() { 
    this.machineService.getAll().subscribe({
      next: data => { 
        this.machines = data;
        this.cdr.detectChanges();
      },
      error: err => console.error('Erreur:', err)
    }); 
  }

  openForm(m?: any) {
    this.showForm = true;
    if (m) {
      this.editId = m.id;
      this.form = { nom: m.nom, etat: m.etat, derniereMaintenance: m.derniereMaintenance };
    } else {
      this.editId = null;
      this.form = { nom: '', etat: 'DISPONIBLE', derniereMaintenance: '' };
    }
  }

  save() {
    const obs = this.editId
      ? this.machineService.update(this.editId, this.form)
      : this.machineService.create(this.form);
    obs.subscribe(() => { 
      this.showForm = false;
      setTimeout(() => this.load(), 100);
    });
  }

  delete(id: number) {
    if (confirm('Supprimer cette machine ?'))
      this.machineService.delete(id).subscribe(() => this.load());
  }

  badgeClass(etat: string) {
    if (etat === 'EN_PANNE') return 'badge badge-panne';
    return 'badge badge-ok';
  }
}
