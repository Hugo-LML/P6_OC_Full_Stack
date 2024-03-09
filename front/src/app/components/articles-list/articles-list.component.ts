import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Article } from 'src/app/core/models/Article';
import { ArticleService } from 'src/app/core/services/article.service';

@Component({
  selector: 'app-articles-list',
  templateUrl: './articles-list.component.html',
  styleUrls: ['./articles-list.component.scss'],
})
export class ArticlesListComponent implements OnInit {
  public articles$: Observable<Article[] | null> = of(null);

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.articles$ = this.articleService.getAllArticles();
  }
}
