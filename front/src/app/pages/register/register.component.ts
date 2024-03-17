import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserRegister } from '../../core/models/User';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  username!: string;
  email!: string;
  password!: string;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {}

  onSubmitForm(form: NgForm): void {
    if (form.value.username && form.value.email && form.value.password) {
      const userRequestBody: UserRegister = {
        username: form.value.username,
        email: form.value.email,
        password: form.value.password,
      };
      this.authService.register(userRequestBody).subscribe({
        next: (_: void) => this.router.navigate(['/login']),
        error: () => alert('Une erreur est survenue lors de l\'inscription'),
      });
    }
  }
}
