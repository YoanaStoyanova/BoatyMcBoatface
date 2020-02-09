import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
    providedIn: 'root'
})
export class AuthorizationService {
    constructor(private jwtHelperService: JwtHelperService) {}

    isAuthorized(allowedRoles: string[]): boolean {
        // check if the list of allowed roles is empty, if empty, authorize the user to access the page
        if (allowedRoles == null || allowedRoles.length === 0) {
            return true;
        }

        // get token
        const rawToken = this.jwtHelperService.tokenGetter();
        if (rawToken === null) {
            return false;
        }

        const token = JSON.parse(rawToken);

        // decode token to read the payload details
        const decodedToken = this.jwtHelperService.decodeToken(token["token"]);

        // check if it was decoded successfully, if not the token is not valid, deny access
        if (!decodedToken || this.jwtHelperService.isTokenExpired(token["token"])) {
            console.log('Invalid token');
            return false;
        }

        console.log(allowedRoles);
        // check if the user roles is in the list of allowed roles, return true if allowed and false if not allowed
        return allowedRoles.includes(decodedToken['auth']);
    }
}