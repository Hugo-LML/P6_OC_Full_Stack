import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { Article } from 'src/app/core/models/Article';
import { Comment, CommentCreate } from 'src/app/core/models/Comment';
import { Theme } from 'src/app/core/models/Theme';
import { ArticleService } from 'src/app/core/services/article.service';
import { CommentService } from 'src/app/core/services/comment.service';
import { ThemeService } from 'src/app/core/services/theme.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss']
})

export class ArticleDetailComponent implements OnInit, OnDestroy {
  private articleService = inject(ArticleService);
  private themeService = inject(ThemeService);
  private commentService = inject(CommentService);
  private userService = inject(UserService);
  private route = inject(ActivatedRoute);
  private formBuilder = inject(FormBuilder);

  article!: Article;
  theme!: Theme;
  comments!: Comment[];
  userUsername!: string;
  createCommentForm!: FormGroup;

  private destroy$: Subject<void> = new Subject<void>();

  // Retrieve all the informations needed to display the article detail page and create a comment
  ngOnInit(): void {
    const articleId = parseInt(this.route.snapshot.paramMap.get('id')!);
    this.articleService.getArticleById(articleId).pipe(takeUntil(this.destroy$)).subscribe((article: Article) => {
      this.article = article;
      this.themeService.getThemeById(article.themeId).pipe(takeUntil(this.destroy$)).subscribe((theme: Theme) => {
        this.theme = theme;
      });
      this.commentService.getAllCommentsFromArticle(articleId).pipe(takeUntil(this.destroy$)).subscribe((comments: Comment[]) => {
        this.comments = comments;
      });
    });
    this.userService.getMe().pipe(takeUntil(this.destroy$)).subscribe((user) => {
      this.userUsername = user.username;
    });
    this.createCommentForm = this.formBuilder.group({
      commentContent: [null, Validators.required],
    });
  }

  // Create a comment and add it to the list of comments displayed on the page when the form is submitted
  onSubmitForm(): void {
    const commentCreated: CommentCreate = {
      userUsername: this.userUsername,
      content: this.createCommentForm.value.commentContent,
      articleId: this.article.id,
    }
    this.commentService.createComment(commentCreated).pipe(takeUntil(this.destroy$)).subscribe({
      next: (commentCreated: Comment) => {
        this.createCommentForm.reset();
        this.comments.push(commentCreated);
      },
      error: () => alert('Une erreur est survenue lors de l\'ajout de votre commentaire'),
    });
  }

  // Unsubscribe from the observables when the component is destroyed
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
