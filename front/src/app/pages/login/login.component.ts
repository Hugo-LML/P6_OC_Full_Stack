import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserLogin } from '../../core/models/User';
import { SessionInformation } from '../../core/models/SessionInformation';
import { SessionService } from '../../core/services/session.service';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit, OnDestroy {
  private authService = inject(AuthService);
  private sessionService = inject(SessionService);
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);

  loginForm!: FormGroup;

  private destroy$: Subject<void> = new Subject<void>();

  // Initialize the form
  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email: [null, Validators.required],
      password: [null, Validators.required],
    });
  }

  // Log in the user and redirect to the article list page when the form is submitted
  onSubmitForm(): void {
    const userRequestBody: UserLogin = {
      email: this.loginForm.value.email,
      password: this.loginForm.value.password,
    };
    this.authService.login(userRequestBody).pipe(takeUntil(this.destroy$)).subscribe({
      next: (response: SessionInformation) => {
        this.sessionService.logIn(response);
        this.router.navigate(['/articles']);
      },
      error: () => alert('Une erreur est survenue lors de la connexion'),
    });
  }

  // Unsubscribe from the observables when the component is destroyed
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
