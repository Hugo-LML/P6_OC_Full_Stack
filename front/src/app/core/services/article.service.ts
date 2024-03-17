import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article, ArticleCreate } from '../models/Article';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ArticleService {

  constructor(private httpClient: HttpClient) {}

  getAllArticles(): Observable<Article[]> {
    return this.httpClient.get<Article[]>(`${environment.apiUrl}/articles`);
  }

  getArticleById(id: number): Observable<Article> {
    return this.httpClient.get<Article>(`${environment.apiUrl}/articles/${id}`);
  }

  createArticle(articleCreated: ArticleCreate): Observable<ArticleCreate> {
    return this.httpClient.post<ArticleCreate>(`${environment.apiUrl}/articles`, articleCreated);
  }
}
