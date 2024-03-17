import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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
export class ArticleDetailComponent implements OnInit {

  article!: Article;
  theme!: Theme;
  comments!: Comment[];

  commentContent!: string;

  userUsername!: string;

  constructor(private articleService: ArticleService, private themeService: ThemeService, private commentService: CommentService, private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    const articleId = parseInt(this.route.snapshot.paramMap.get('id')!);
    this.articleService.getArticleById(articleId).subscribe((article: Article) => {
      this.article = article;
      this.themeService.getThemeById(article.themeId).subscribe((theme: Theme) => {
        this.theme = theme;
      });
      this.commentService.getAllCommentsFromArticle(articleId).subscribe((comments: Comment[]) => {
        this.comments = comments;
      });
    });
    this.userService.getMe().subscribe((user) => {
      this.userUsername = user.username;
    });
  }

  onSubmitComment(form: NgForm): void {
    if (this.userUsername, form.value.commentContent) {
      const commentCreated: CommentCreate = {
        userUsername: this.userUsername,
        content: form.value.commentContent,
        articleId: this.article.id,
      }
      this.commentService.createComment(commentCreated).subscribe({
        next: (commentCreated: Comment) => {
          this.commentContent = ''
          this.comments.push(commentCreated);
        },
        error: () => alert('Une erreur est survenue lors de l\'ajout de votre commentaire'),
      });
    }
  }
  
}
