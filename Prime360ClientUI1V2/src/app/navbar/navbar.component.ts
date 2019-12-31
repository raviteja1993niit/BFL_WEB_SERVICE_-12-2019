import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../core/auth.service';
import { TokenStorage } from '../core/token.storage';
import { HttpErrorResponse } from '@angular/common/http';
import { User } from '../Model/user';
import { NavbarParentDto } from '../Model/navbar-parent-dto';
import { NavbarService } from '../core/navbar.service';
import * as toastr from 'toastr';
import { MenuStorageService } from '../core/menu-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  currentUser: User;
  navbarParentDto: NavbarParentDto[];
  static menusItem : string="";
  currentDate : Date;
  constructor(private token: TokenStorage, private navbarService: NavbarService, private authService: AuthService, private router: Router, private menuStorage : MenuStorageService) {
    setInterval(() => {
      this.currentDate = new Date();
    }, 1);
  }

  ngOnInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if(this.currentUser !== null && typeof this.currentUser !== 'undefined'){
      this.getMenus();
      // NavbarComponent.menusItem = this.menuStorage.getMenus();
      // if("" === NavbarComponent.menusItem || null === NavbarComponent.menusItem || typeof NavbarComponent.menusItem === 'undefined'){
      //   this.getMenus();
      // } else {
      //   this.navbarParentDto = JSON.parse(this.menuStorage.getMenus());
      // } 
    } else {
      this.router.navigate(['login']);
    }
  }

  getMenus() {
    this.navbarService.getNavMenu(this.currentUser.roleID).subscribe(data => {
      this.navbarParentDto = data
      console.log(this.navbarParentDto)
    }, (err: HttpErrorResponse) => {
      if (null === err.error) {
        toastr.error("Session timeout.!");
        this.router.navigate(['login']);
      } else if (err.status === 500) {
        toastr.error("Session timeout.!");
        this.router.navigate(['login']);
      } else {
        toastr.error(err.error.message);
      }
    });
  }

  // getMenus() {
  //   this.navbarService.getNavMenu(this.currentUser.roleID).subscribe(data => {
  //     this.navbarParentDto = data;
  //     this.menuStorage.saveMenus(this.navbarParentDto);
  //     console.log(JSON.stringify(this.navbarParentDto));
  //   },(err) =>{
  //     if(null === err || typeof err.status === 'undefined'){
  //       toastr.error("Token Expired !");
  //     }
  //     if(err.error !== 'undifined'){
  //       toastr.error(err.error.message);	
  //     }
  //     console.log("getNavMenu : "+err.error+" status "+err.status);
  //     this.router.navigate(['login']);
  //   });
  // }

  logout(): void {

    console.log('befor logout Session token ::');
    if (this.token.getToken() != null) {
      console.log('Session token ::' + this.token.getToken());
      this.authService.logout(this.token.getToken()).subscribe(
        data => {
          // alert("User logout successfully.");
          this.token.signOut();
          this.router.navigate(['login']);
        }, (err: HttpErrorResponse) => {
          if (err.error instanceof Error) {
            //A client-side or network error occurred.				 
            console.log('An error occurred:', err.error.message);
            alert("Error 1" + err.error.message);
            // this.toastr.error(err.error.message);
            // this.toastr.error(err.error.message+" : "+err.status, "Error...");
          } else {
            // this.showError("Invalid credentials");
            //Backend returns unsuccessful response codes such as 404, 500 etc.		  		 
            console.log('Backend returned status code: ', err.status);
            console.log('Response body:', err.name);
            console.log('Response body:', err.message);
            console.log('Response body:', err.error);
            alert("Error 2" + err.error.message);
            // this.loaderService.display(false);
            // this.toastr.error(err.error.message+" : "+err.status, "Bed Credintial...");
          }
        }
      );
    } else {
      alert("Logout ==>");
      this.router.navigate(['login']);
    }
  }

  opneEodScreen() : void{
    console.log("opennnn")
    this.router.navigate(['eodupload']);
    this.router.navigate(['negativeupload']);
    
  }
}
