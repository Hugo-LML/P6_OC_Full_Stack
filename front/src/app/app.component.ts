import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionService } from './core/services/session.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(public router: Router, private sessionService: SessionService) { }

  ngOnInit(): void {
  }

  public $isLogged(): Observable<boolean> {
    return this.sessionService.$isLogged();
  }

}
