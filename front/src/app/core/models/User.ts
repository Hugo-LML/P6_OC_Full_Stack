import { Theme } from "./Theme";

export interface User {
  id: number;
  username: string;
  email: string;
  password: string;
  themes: Theme[];
  createdAt: string;
  updatedAt: string;
}

export type UserRegister = Pick<User, 'username' | 'email' | 'password'>;

export type UserLogin = Pick<User, 'email' | 'password'>;

export type UserUpdate = Pick<User, 'username' | 'email'>;