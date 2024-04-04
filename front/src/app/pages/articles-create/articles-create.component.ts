import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { Theme } from 'src/app/core/models/Theme';
import { ArticleService } from 'src/app/core/services/article.service';
import { ThemeService } from 'src/app/core/services/theme.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-articles-create',
  templateUrl: './articles-create.component.html',
  styleUrls: ['./articles-create.component.scss'],
})
export class ArticlesCreateComponent implements OnInit, OnDestroy {
  private themeService = inject(ThemeService);
  private userService = inject(UserService);
  private articleService = inject(ArticleService);
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);

  themes: Theme[] = [];
  userUsername!: string;
  createArticleForm!: FormGroup;

  private destroy$: Subject<void> = new Subject<void>();

  // Retrieve all the themes to display them in the form to create an article and get the username of the connected user
  ngOnInit(): void {
    this.userService.getMe().pipe(takeUntil(this.destroy$)).subscribe((user) => {
      this.userUsername = user.username;
    });
    this.themeService.getAllThemes().pipe(takeUntil(this.destroy$)).subscribe((themes) => {
      this.themes = themes;
    });
    this.createArticleForm = this.formBuilder.group({
      articleThemeId: [null, Validators.required],
      articleTitle: [null, Validators.required],
      articleContent: [null, Validators.required],
    });
  }

  // Create an article and redirect to the article list page when the form is submitted
  onSubmitForm(): void {
    const articleRequestBody = {
      userUsername: this.userUsername,
      themeId: this.createArticleForm.value.articleThemeId,
      title: this.createArticleForm.value.articleTitle,
      content: this.createArticleForm.value.articleContent,
    };
    this.articleService.createArticle(articleRequestBody).pipe(takeUntil(this.destroy$)).subscribe({
      next: () =>
        this.router.navigate(['/articles']),
    });
  }

  // Unsubscribe from the observables when the component is destroyed
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
