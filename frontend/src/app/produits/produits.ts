import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProduitService } from '../services/produit.service';

@Component({
  selector: 'app-produits',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './produits.html',
  styleUrl: './produits.css'
})
export class ProduitsComponent implements OnInit {
  produits: any[] = [];
  form: any = { nom: '', type: '', stock: 0, fournisseur: '' };
  editId: number | null = null;
  showForm = false;

  constructor(private produitService: ProduitService, private cdr: ChangeDetectorRef) {}

  ngOnInit() { this.load(); }

  load() { 
    this.produitService.getAll().subscribe(data => { this.produits = data; this.cdr.detectChanges(); }); 
  }

  openForm(p?: any) {
    this.showForm = true;
    if (p) {
      this.editId = p.id;
      this.form = { nom: p.nom, type: p.type, stock: p.stock, fournisseur: p.fournisseur };
    } else {
      this.editId = null;
      this.form = { nom: '', type: '', stock: 0, fournisseur: '' };
    }
  }

  save() {
    const obs = this.editId
      ? this.produitService.update(this.editId, this.form)
      : this.produitService.create(this.form);
    obs.subscribe(() => { this.load(); this.showForm = false; });
  }

  delete(id: number) {
    if (confirm('Supprimer ce produit ?'))
      this.produitService.delete(id).subscribe(() => this.load());
  }

  stockClass(stock: number) {
    if (stock === 0) return 'badge badge-panne';
    if (stock < 10) return 'badge badge-attente';
    return 'badge badge-ok';
  }
}
