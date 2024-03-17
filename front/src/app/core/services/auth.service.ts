import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserLogin, UserRegister } from "../models/User";
import { environment } from "src/environments/environment";
import { Observable } from "rxjs";
import { SessionInformation } from "../models/SessionInformation";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  constructor(private httpClient: HttpClient) {}

  register(registerRequestBody: UserRegister): Observable<void> {
    return this.httpClient.post<void>(`${environment.apiUrl}/auth/register`, registerRequestBody);
  }

  login(loginRequestBody: UserLogin): Observable<SessionInformation> {
    return this.httpClient.post<SessionInformation>(`${environment.apiUrl}/auth/login`, loginRequestBody);
  }

}