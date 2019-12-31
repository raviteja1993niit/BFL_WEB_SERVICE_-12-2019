import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';


import { PasswordDto } from '../../Model/password-dto'
import { PasswordService } from '../../core/password.service';
import { ActivatedRoute, Router } from '@angular/router';
import * as toastr from 'toastr';
import { TokenStorage } from '../../core/token.storage';
import { AppComponent } from '../../app.component';
import { HttpErrorResponse } from '@angular/common/http';
import { ConfirmPasswordValidator } from '../../confirm-password-validator';


@Component({
  selector: 'app-resetpassword',
  templateUrl: './resetpassword.component.html',
  styleUrls: ['./resetpassword.component.css']
})
export class ResetpasswordComponent implements OnInit {

  resetFormGroup: FormGroup;
  confirmPasswordFormGroup: FormGroup;
  passwordDto: PasswordDto = new PasswordDto();
  minNum: number = 4;
  maxNum: number = 20;
  constructor(private formBuilder: FormBuilder, private token: TokenStorage, private passwordService: PasswordService, private router: Router, private route: ActivatedRoute, private location: Location) {

  }

  ngOnInit() {
    this.confirmPasswordFormGroup = this.formBuilder.group({
      newPassword: new FormControl('', [Validators.required, Validators.minLength(this.minNum), Validators.maxLength(this.maxNum)]),
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(this.minNum), Validators.maxLength(this.maxNum)])
    }, {
        validator: ConfirmPasswordValidator.validate.bind(this)
      });
    this.resetFormGroup = this.formBuilder.group({
      currentPassword: new FormControl('', [Validators.required, Validators.minLength(this.minNum), Validators.maxLength(this.maxNum)]),
      confirmPasswordFormGroup: this.confirmPasswordFormGroup
    });
  }

  resetPassword() {
    $('.page-loader__spinner').css('display','block');
    // this.passwordDto.newPassword = this.passwordDto.confirmPassword;
    this.passwordDto.userType = AppComponent.menuType;
    console.log("Change Password : " + JSON.stringify(this.passwordDto));

    this.passwordService.changePassword(this.passwordDto).subscribe(res => {
      if (res.success) {
        // this.users = res.data;
        console.log("Succes Message " + res.message);
        this.token.signOut();
        this.router.navigate(['/login']);
        toastr.success("Password changed successfully !")
      } else {
        console.log("Error Message " + res.message);
      }

    }, (err: HttpErrorResponse) => {
      if (err.error instanceof Error) {
        console.log('An error occurred:', err.error.message);
        toastr.error(err.error.message);
      } else {
        //Backend returns unsuccessful response codes such as 404, 500 etc.		  		 
        console.log('Backend returned status code: ', err.status);
        if (null === err.error) {
          toastr.error("Session timeout.!");
          this.router.navigate(['login']);
        }
        toastr.error(err.error.message);	
      }
    });

  }

  goBack(): void {
    this.location.back();
  }

}
