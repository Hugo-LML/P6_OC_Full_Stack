import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserRegister } from '../../core/models/User';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit, OnDestroy {

  private authService = inject(AuthService);
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);

  registerForm!: FormGroup;

  private destroy$: Subject<void> = new Subject<void>();

  // Initialize the form
  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: [null, Validators.required],
      email: [null, Validators.required],
      password: [null, Validators.required],
    });
  }

  // Register the user and redirect to the login page when the form is submitted
  onSubmitForm(): void {
    const userRequestBody: UserRegister = {
      username: this.registerForm.value.username,
      email: this.registerForm.value.email,
      password: this.registerForm.value.password,
    };
    this.authService.register(userRequestBody).pipe(takeUntil(this.destroy$)).subscribe({
      next: () => this.router.navigate(['/login']),
      error: () => alert('Une erreur est survenue lors de l\'inscription'),
    });
  }

  // Unsubscribe from the observables when the component is destroyed
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
