import { Component, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from 'src/app/core/models/Article';
import { ArticleService } from 'src/app/core/services/article.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent {
  
  private articleService = inject(ArticleService);

  public articles$: Observable<Article[]> = this.articleService.getAllArticles();

}
