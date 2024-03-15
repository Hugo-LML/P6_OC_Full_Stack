import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment, CommentCreate } from '../models/Comment';

@Injectable({
  providedIn: 'root',
})
export class CommentService {

  constructor(private httpClient: HttpClient) {}

  getAllCommentsFromArticle(articleId: number): Observable<Comment[]> {
    return this.httpClient.get<Comment[]>(`${environment.apiUrl}/comments/${articleId}`);
  }

  createComment(commentCreated: CommentCreate): Observable<Comment> {
    return this.httpClient.post<Comment>(`${environment.apiUrl}/comments`, commentCreated);
  }

}
