import { Injectable } from '@angular/core';
import {
    HttpInterceptor, HttpRequest, HttpHandler, HttpSentEvent, HttpHeaderResponse, HttpProgressEvent,
    HttpResponse, HttpUserEvent, HttpErrorResponse
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { TokenStorage } from './token.storage';
import { tap } from 'rxjs/operators';
import * as toastr from 'toastr';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class Interceptor implements HttpInterceptor {

    constructor(private token: TokenStorage, private router: Router) { }

    intercept(req: HttpRequest<any>, next: HttpHandler):
        Observable<HttpSentEvent | HttpHeaderResponse | HttpProgressEvent | HttpResponse<any> | HttpUserEvent<any>> {
        // add authorization header with jwt token if available
        console.log("intercepted request ... ");
        let authReq = req;
        if (this.token.getToken() != null) {
            authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken()) });
        }
        console.log("Sending request with new header now ... ");

        //send the newly created request
        return next.handle(authReq).pipe(
            tap(event => {
                if (event instanceof HttpResponse) {
                    // http response status code for success
                    console.log(" Successfully response with status code : " + event.status);
                }
            }, error => {
                // http response status code for error
                // console.log("----response----");
                // console.error("status code:");
                // console.error(error.status);
                // console.error("Error : "+error.message+" status code : "+error.status);
                if (error.status === 0 || error.status === 400 || error.status === 401 || error.status === 403 || error.status === 500) {
                    if (error.status === 0) {
                        toastr.error("Internal server error.");
                    }
                    this.router.navigate(['login']);
                }
                //    console.log(error.status+" : "+error.error.message);
                console.log("--- end of response---");
            })
        );
    }

}
