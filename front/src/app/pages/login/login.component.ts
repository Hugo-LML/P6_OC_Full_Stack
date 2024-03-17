import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { UserLogin } from '../../core/models/User';
import { SessionInformation } from '../../core/models/SessionInformation';
import { SessionService } from '../../core/services/session.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  email!: string;
  password!: string;

  constructor(
    private authService: AuthService,
    private sessionService: SessionService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  onSubmitForm(form: NgForm): void {
    if (form.value.email && form.value.password) {
      const userRequestBody: UserLogin = {
        email: form.value.email,
        password: form.value.password,
      };
      this.authService.login(userRequestBody).subscribe({
        next: (response: SessionInformation) => {
          this.sessionService.logIn(response);
          this.router.navigate(['/articles']);
        },
        error: () => alert('Une erreur est survenue lors de la connexion'),
      });
    }
  }
}
