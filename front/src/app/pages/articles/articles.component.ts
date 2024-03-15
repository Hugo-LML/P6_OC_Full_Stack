import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Article } from 'src/app/core/models/Article';
import { ArticleService } from 'src/app/core/services/article.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
  
  public articles$: Observable<Article[] | null> = of(null);

  constructor(private articleService: ArticleService, private router: Router) {}

  ngOnInit(): void {
    this.articles$ = this.articleService.getAllArticles();
  }

  redirectToCreateArticle(): void {
    this.router.navigate(['/articles/create']);
  }

  redirectToArticleDetail(articleId: number): void {
    this.router.navigate(['/articles', articleId]);
  }

}
