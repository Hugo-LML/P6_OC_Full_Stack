import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root',
})
export class UserService {

  constructor(private httpClient: HttpClient) {}

  getMe(): Observable<Omit<User, 'password'>> {
    return this.httpClient.get<Omit<User, 'password'>>(`${environment.apiUrl}/users/me`);
  }

}
