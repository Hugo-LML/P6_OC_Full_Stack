import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ArticleCreate } from 'src/app/core/models/Article';
import { Theme } from 'src/app/core/models/Theme';
import { ArticleService } from 'src/app/core/services/article.service';
import { ThemeService } from 'src/app/core/services/theme.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-articles-create',
  templateUrl: './articles-create.component.html',
  styleUrls: ['./articles-create.component.scss']
})
export class ArticlesCreateComponent implements OnInit {

  themes: Theme[] = [];

  userUsername!: string;
  articleThemeId!: number;
  articleTitle!: string;
  articleContent!: string;

  constructor(private userService: UserService, private articleService: ArticleService, private router: Router, private themeService: ThemeService) { }

  ngOnInit(): void {
    this.userService.getMe().subscribe((user) => {
      this.userUsername = user.username;
    });
    this.themeService.getAllThemes().subscribe((themes) => {
      this.themes = themes;
    });
  }

  onSubmitForm(form: NgForm): void {
    if (this.articleThemeId && form.value.articleTitle && form.value.articleContent) {
      const articleRequestBody = {
        userUsername: this.userUsername,
        themeId: this.articleThemeId,
        title: form.value.articleTitle,
        content: form.value.articleContent,
      }
      this.articleService.createArticle(articleRequestBody).subscribe({
        next: (articleCreated: ArticleCreate) => this.router.navigate(['/articles'])
      });
    }
  }

}
