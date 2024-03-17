export interface Comment {
  id: number;
  userUsername: string;
  articleId: number;
  content: string;
  createdAt: string;
}

export type CommentCreate = Pick<Comment, 'userUsername' | 'articleId' | 'content'>;