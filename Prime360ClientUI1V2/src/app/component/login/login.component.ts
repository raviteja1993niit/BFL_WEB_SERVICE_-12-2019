import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { FormGroup, FormBuilder, FormControl ,Validators} from '@angular/forms';
import { LoginUser } from '../../Model/login-user';
import { User } from '../../Model/user';
import { TokenStorage } from '../../core/token.storage';
import { AuthService } from '../../core/auth.service';
import * as toastr from 'toastr';
import { MenuStorageService } from '../../core/menu-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  formGroup: FormGroup;
  currentUser: User;
  loginuser: LoginUser = new LoginUser();
  minNum : number= 4;
  maxNum : number= 20;
  constructor(private router: Router,private formbuilder:FormBuilder, private authService: AuthService, private token: TokenStorage, private menuStorage : MenuStorageService) { }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (typeof this.currentUser !== 'undefined') {
      localStorage.removeItem('currentUser');
    }
    if (null !== this.token.getToken()) {
      this.token.signOut();
    }
    this.formGroup = this.formbuilder.group({
      userId: new FormControl('',Validators.required),
      password: new FormControl('', [Validators.required, Validators.minLength(this.minNum),Validators.maxLength(this.maxNum)])
    });
  }

  login(): void {
    $('.page-loader__spinner').css('display','block');
    this.menuStorage.clearMenus();
    console.log("menus : "+this.menuStorage.getMenus());
    this.authService.attemptAuth(this.loginuser.userId, this.loginuser.password).subscribe(data => {
      console.log("Login success...!")
      this.token.saveToken(data.token);
      localStorage.setItem('currentUser', JSON.stringify(data.user));
      // this.menuDesplay = true;
      if (data.status) {
        this.router.navigate(['resetpassword']);
        // this.router.navigate(['edit-usercomponent/'+this.loginuser.userId]);
      } else {
        this.router.navigate(['welcome']);
      }

    }, (err: HttpErrorResponse) => {
      if (err.error instanceof Error) {
        //A client-side or network error occurred.		
         toastr.error(err.error.message);		 
        console.log('An error occurred:', err.error.message);
      } else {
        if(err.status !== 0){
          toastr.error(err.error.message);
        }
        //Backend returns unsuccessful response codes such as 404, 500 etc.				 
        console.log('Backend returned status code: ', err.status);
        console.log('Response body:', err.error);
      }
    });
  }


  cancel(): void {
    this.loginuser.userId = "";
    this.loginuser.password = "";
  }

}
