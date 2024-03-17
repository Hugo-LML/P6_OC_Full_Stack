import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from './core/services/session.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  screenWidth: number;
  isSidenavOpen = false;

  constructor(public router: Router, private sessionService: SessionService) {
    this.screenWidth = window.innerWidth;
  }

  ngOnInit(): void {
  }

  toggleSidenav(): void {
    this.isSidenavOpen = !this.isSidenavOpen;
  }

  closeSidenav(): void {
    this.isSidenavOpen = false;
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: any) {
    this.screenWidth = window.innerWidth;
  }

  public $isLogged(): Observable<boolean> {
    return this.sessionService.$isLogged();
  }

  isLoginPage(): boolean {
    return this.router.url.includes('/login') || this.router.url.includes('/register');
  }

}
