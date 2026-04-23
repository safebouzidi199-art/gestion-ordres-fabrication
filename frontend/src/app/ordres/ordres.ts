import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OrdreService } from '../services/ordre.service';
import { ProduitService } from '../services/produit.service';

@Component({
  selector: 'app-ordres',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ordres.html',
  styleUrl: './ordres.css'
})
export class OrdresComponent implements OnInit {
  ordres: any[] = [];
  produits: any[] = [];
  form: any = { projet: '', quantite: 1, date: '', produit: { id: null } };
  showForm = false;
  etats = ['EN_ATTENTE', 'EN_COURS', 'TERMINE'];

  constructor(private ordreService: OrdreService, private produitService: ProduitService, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.load();
    this.produitService.getAll().subscribe(p => { this.produits = p; this.cdr.detectChanges(); });
  }

  load() { 
    this.ordreService.getAll().subscribe(data => { this.ordres = data; this.cdr.detectChanges(); }); 
  }

  openForm() {
    this.showForm = true;
    this.form = { projet: '', quantite: 1, date: '', produit: { id: null } };
  }

  save() {
    this.ordreService.create(this.form).subscribe({
      next: () => { this.load(); this.showForm = false; },
      error: (err) => alert(err.error?.message || 'Erreur lors de la création')
    });
  }

  updateEtat(id: number, etat: string) {
    this.ordreService.updateEtat(id, etat).subscribe(() => this.load());
  }

  delete(id: number) {
    if (confirm('Supprimer cet ordre ?'))
      this.ordreService.delete(id).subscribe(() => this.load());
  }

  badgeClass(etat: string) {
    if (etat === 'EN_ATTENTE') return 'badge badge-attente';
    if (etat === 'EN_COURS') return 'badge badge-cours';
    return 'badge badge-termine';
  }
}
