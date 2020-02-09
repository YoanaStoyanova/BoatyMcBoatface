import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {environment} from "../../environments/environment";


@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getCurrent() {
        return this.http.get(`${environment.baseUrl}/me`);
    }

    getById(id: number) {
        return this.http.get(`${environment.baseUrl}/users/${id}`);
    }

    delete(id: number) {
        return this.http.delete(`${environment.baseUrl}/users/${id}`);
    }
}