import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OrdreService } from '../services/ordre.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class DashboardComponent implements OnInit {
  ordres: any[] = [];
  etats = ['EN_ATTENTE', 'EN_COURS', 'TERMINE'];

  get enAttente() { return this.ordres.filter(o => o.etat === 'EN_ATTENTE').length; }
  get enCours() { return this.ordres.filter(o => o.etat === 'EN_COURS').length; }
  get termine() { return this.ordres.filter(o => o.etat === 'TERMINE').length; }

  constructor(
    private ordreService: OrdreService,
    public authService: AuthService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() { this.load(); }

  load() {
    this.ordreService.getAll().subscribe(data => {
      this.ordres = data;
      this.cdr.detectChanges();
    });
  }

  updateEtat(id: number, etat: string) {
    this.ordreService.updateEtat(id, etat).subscribe(() => this.load());
  }

  badgeClass(etat: string) {
    if (etat === 'EN_ATTENTE') return 'badge badge-attente';
    if (etat === 'EN_COURS') return 'badge badge-cours';
    return 'badge badge-termine';
  }
}
