import { Component, HostListener, inject } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from './core/services/session.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  private sessionService = inject(SessionService);
  public router = inject(Router);
  
  screenWidth: number = window.innerWidth;
  isSidenavOpen = false;

  // Toggle the sidenav
  toggleSidenav(): void {
    this.isSidenavOpen = !this.isSidenavOpen;
  }

  // Close the sidenav
  closeSidenav(): void {
    this.isSidenavOpen = false;
  }

  // Set the screenWidth property when the window is resized
  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.screenWidth = window.innerWidth;
  }

  // Check if the user is logged in
  public $isLogged(): Observable<boolean> {
    return this.sessionService.$isLogged();
  }

  // Check if the current page is the login page
  isLoginPage(): boolean {
    return this.router.url.includes('/login') || this.router.url.includes('/register');
  }

}
