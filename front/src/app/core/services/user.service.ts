import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User, UserUpdate } from '../models/User';

@Injectable({
  providedIn: 'root',
})
export class UserService {

  constructor(private httpClient: HttpClient) {}

  getMe(): Observable<Omit<User, 'password'>> {
    return this.httpClient.get<Omit<User, 'password'>>(`${environment.apiUrl}/users/me`);
  }

  updateMe(userUpdate: UserUpdate): Observable<Omit<User, 'password'>> {
    return this.httpClient.put<Omit<User, 'password'>>(`${environment.apiUrl}/users/me`, userUpdate);
  }

  subscribeToTheme(themeId: number): Observable<void> {
    return this.httpClient.put<void>(`${environment.apiUrl}/users/subscribe/${themeId}`, { themeId });
  }

  unSubscribeToTheme(themeId: number): Observable<void> {
    return this.httpClient.put<void>(`${environment.apiUrl}/users/unsubscribe/${themeId}`, { themeId });
  }

}
