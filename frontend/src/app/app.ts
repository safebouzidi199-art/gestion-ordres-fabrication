import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  constructor(private authService: AuthService) {}
  isLoggedIn() { return this.authService.isLoggedIn(); }
  getUsername() { return this.authService.getUsername(); }
  getRole() { return this.authService.getRole().replace('ROLE_', ''); }
  logout() { this.authService.logout(); }
}
