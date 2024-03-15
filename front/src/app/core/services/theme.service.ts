import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Theme } from '../models/Theme';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {

  constructor(private httpClient: HttpClient) {}

  getAllThemes(): Observable<Theme[]> {
    return this.httpClient.get<Theme[]>(`${environment.apiUrl}/themes`);
  }

  getThemeById(id: number): Observable<Theme> {
    return this.httpClient.get<Theme>(`${environment.apiUrl}/themes/${id}`);
  }

}
