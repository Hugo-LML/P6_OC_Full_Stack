import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Theme } from 'src/app/core/models/Theme';
import { User } from 'src/app/core/models/User';
import { ThemeService } from 'src/app/core/services/theme.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit, OnDestroy {
  private themeService = inject(ThemeService);
  private userService = inject(UserService);

  public themes$: Observable<Theme[]> = this.themeService.getAllThemes();
  
  user!: Omit<User, 'password'>;
  private userSubscription: Subscription | undefined;

  // Retrieve the connected user
  ngOnInit(): void {
    this.userSubscription = this.userService.getMe().subscribe((user) => {
      this.user = user;
    });
  }

  // Return the text to display on the button to subscribe or unsubscribe to a theme
  getButtonText(theme: Theme): string {
    return this.user?.themes.find(t => t.id === theme.id) ? "Se désabonner" : "S'abonner";
  }

  // Subscribe or unsubscribe to a theme when the button is clicked
  onSubscribeOrUnsubscribe(themeId: number): void {
    if (this.user.themes.find((theme) => theme.id === themeId)) {
      this.userService.unSubscribeToTheme(themeId).subscribe(() => {
        this.user.themes = this.user.themes.filter((theme) => theme.id !== themeId);
      });
    } else {
      this.userService.subscribeToTheme(themeId).subscribe(() => {
        this.themeService.getThemeById(themeId).subscribe((theme) => this.user.themes.push(theme));
      });
    }
  }

  // Unsubscribe from the observable when the component is destroyed
  ngOnDestroy(): void {
    if (this.userSubscription) {
      this.userSubscription.unsubscribe();
    }
  }

}
