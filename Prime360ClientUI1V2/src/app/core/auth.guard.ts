import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { TokenStorage } from './token.storage';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router, private token: TokenStorage) { }

    canActivate(route: ActivatedRouteSnapshot,  state: RouterStateSnapshot) {
        // if (localStorage.getItem('currentUser')) {
        if (this.token.getToken() != null) {
            // logged in so return true
            return true;
        }
        // not logged in so redirect to login page with the return url
        this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
        return false;
    }
}