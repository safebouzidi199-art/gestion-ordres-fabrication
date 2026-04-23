import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login';
import { EmployesComponent } from './employes/employes';
import { MachinesComponent } from './machines/machines';
import { ProduitsComponent } from './produits/produits';
import { OrdresComponent } from './ordres/ordres';
import { DashboardComponent } from './dashboard/dashboard';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [authGuard] },
  { path: 'employes', component: EmployesComponent, canActivate: [authGuard] },
  { path: 'machines', component: MachinesComponent, canActivate: [authGuard] },
  { path: 'produits', component: ProduitsComponent, canActivate: [authGuard] },
  { path: 'ordres', component: OrdresComponent, canActivate: [authGuard] },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' }
];
