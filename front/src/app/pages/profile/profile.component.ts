import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Theme } from 'src/app/core/models/Theme';
import { UserUpdate } from 'src/app/core/models/User';
import { ThemeService } from 'src/app/core/services/theme.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  themes: Theme[] = [];

  username!: string;
  email!: string;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.getMe().subscribe((user) => {
      this.username = user.username;
      this.email = user.email;
      this.themes = user.themes;
    });
  }

  onSubmitForm(form: NgForm): void {
    if (form.value.username && form.value.email) {
      const userRequestBody: UserUpdate = {
        username: this.username,
        email: this.email,
      }
      this.userService.updateMe(userRequestBody).subscribe({
        next: () => alert('Profile updated'),
        error: () => alert('An error occurred'),
      });
    }
  }

  onUnsubscribe(themeId: number): void {
    this.userService.unSubscribeToTheme(themeId).subscribe(() => {
      this.userService.unSubscribeToTheme(themeId).subscribe(() => {
        this.themes = this.themes.filter((theme) => theme.id !== themeId);
      });
    });
  }

}
