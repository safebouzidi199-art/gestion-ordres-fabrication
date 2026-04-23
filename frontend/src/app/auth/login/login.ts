import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class LoginComponent {
  username = '';
  password = '';
  error = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.error = '';
    this.authService.login(this.username, this.password).subscribe({
      next: (res) => {
        if (res.role === 'RESPONSABLE_PRODUCTION') {
          this.router.navigate(['/dashboard']);
        } else {
          this.router.navigate(['/employes']);
        }
      },
      error: () => this.error = 'Identifiants incorrects'
    });
  }
}
