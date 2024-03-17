export interface Article {
  id: number;
  title: string;
  userUsername: string;
  themeId: number;
  content: string;
  createdAt: string;
}

export type ArticleCreate = Pick<Article, 'userUsername' | 'themeId' | 'title' | 'content'>;