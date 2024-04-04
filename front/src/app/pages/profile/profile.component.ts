import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { Theme } from 'src/app/core/models/Theme';
import { UserUpdate } from 'src/app/core/models/User';
import { SessionService } from 'src/app/core/services/session.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy {
  private userService = inject(UserService);
  private sessionService = inject(SessionService);
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);

  themes: Theme[] = [];
  userEmail: string = '';

  updateProfileForm!: FormGroup;

  private destroy$: Subject<void> = new Subject<void>();

  // Retrieve the connected user's information to display them in the form and get the themes he is subscribed to
  ngOnInit(): void {
    this.userService.getMe().pipe(takeUntil(this.destroy$)).subscribe((user) => {
      this.userEmail = user.email;
      this.updateProfileForm = this.formBuilder.group({
        username: [user.username, Validators.required],
        email: [user.email, Validators.required],
      });
      this.themes = user.themes;
    });
  }

  // Update the connected user's profile when the form is submitted
  onSubmitForm(): void {
    const userRequestBody: UserUpdate = {
      username: this.updateProfileForm.value.username,
      email: this.updateProfileForm.value.email,
    }
    this.userService.updateMe(userRequestBody).pipe(takeUntil(this.destroy$)).subscribe({
      next: () => alert('Votre profil a été mis à jour avec succès'),
      error: () => alert('Une erreur est survenue lors de la mise à jour de votre profil'),
    });

    if (this.updateProfileForm.value.email !== this.userEmail) {
      this.onLogout();
    }
  }

  // Unsubscribe from a theme and remove it from the list of themes displayed on the page
  onUnsubscribe(themeId: number): void {
    this.userService.unSubscribeToTheme(themeId).pipe(takeUntil(this.destroy$)).subscribe(() => {
      this.themes = this.themes.filter((theme) => theme.id !== themeId);
    });
  }

  // Log out the user and redirect to the home page
  onLogout(): void {
    this.sessionService.logOut();
    this.router.navigate(['/']);
  }

  // Unsubscribe from the observables when the component is destroyed
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

}
