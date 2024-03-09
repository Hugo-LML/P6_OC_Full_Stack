export interface User {
  id: number;
  username: string;
  email: string;
  password: string;
  createdAt: string;
  updatedAt: string;
}

export type UserRegister = Pick<User, 'username' | 'email' | 'password'>;

export type UserLogin = Pick<User, 'email' | 'password'>;