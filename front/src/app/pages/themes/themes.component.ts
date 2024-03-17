import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Theme } from 'src/app/core/models/Theme';
import { User } from 'src/app/core/models/User';
import { ThemeService } from 'src/app/core/services/theme.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {

  public themes$: Observable<Theme[] | null> = of(null);
  
  user!: Omit<User, 'password'>;

  constructor(private themeService: ThemeService, private userService: UserService) {}

  ngOnInit(): void {
    this.themes$ = this.themeService.getAllThemes();
    this.userService.getMe().subscribe((user) => {
      this.user = user;
    });
  }

  getButtonText(theme: Theme): string {
    return this.user?.themes.find(t => t.id === theme.id) ? "Se désabonner" : "S'abonner";
  }

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

}
