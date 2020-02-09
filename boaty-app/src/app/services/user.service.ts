import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {environment} from "../../environments/environment";
import {UserModel} from "../model/user-model";
import {SignupModel} from "../model/signup-model";


@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    public createUser(user :SignupModel) {
        return this.http.post<UserModel>(`${environment.baseUrl}/users`, user);
    }

    getCurrent() {
        return this.http.get<UserModel>(`${environment.baseUrl}/me`);
    }

    getById(id: number) {
        return this.http.get<UserModel>(`${environment.baseUrl}/users/${id}`);
    }

    delete(id: number) {
        return this.http.delete(`${environment.baseUrl}/users/${id}`);
    }
}