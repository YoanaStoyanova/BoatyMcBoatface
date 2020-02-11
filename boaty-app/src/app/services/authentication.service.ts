import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import {environment} from "../../environments/environment";
import {TokenModel} from "../model/token-model";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private tokenSubject: BehaviorSubject<string>;
    public token: Observable<string>;

    constructor(private http: HttpClient, private jwtHelperService: JwtHelperService) {
        this.tokenSubject = new BehaviorSubject<string>(this.jwtHelperService.tokenGetter());
        this.token = this.tokenSubject.asObservable();
    }

    public isAuthenticated(): boolean {
        return this.tokenSubject.value != null;
    }

    public get tokenValue(): string {
        return this.tokenSubject.value;
    }

    login(username: string, password: string) {
        const httpOptions = {
            headers: new HttpHeaders({
                'Authorization': 'Basic ' + btoa(`${username}:${password}`)
            })
        };

        return this.http.get<TokenModel>(`${environment.baseUrl}/tokens`, httpOptions)
            .pipe(map(token => {
                // login successful if there's a jwt token in the response
                if (token && token.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('token', JSON.stringify(token));
                    this.tokenSubject.next(token.token);
                }
                return token;
            }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('token');
        this.tokenSubject.next(null);
    }
}