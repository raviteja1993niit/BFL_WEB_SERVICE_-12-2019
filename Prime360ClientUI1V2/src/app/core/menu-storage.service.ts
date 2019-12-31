import { Injectable } from '@angular/core';
import { NavbarParentDto } from '../Model/navbar-parent-dto';
const MENU_KEY = 'menus';

@Injectable({
  providedIn: 'root'
})

export class MenuStorageService {
  

  constructor() { }

  public clearMenus() {
    window.sessionStorage.removeItem(MENU_KEY);
  }

  public saveMenus(menus: NavbarParentDto[]) {
    window.sessionStorage.removeItem(MENU_KEY);
    window.sessionStorage.setItem(MENU_KEY,  JSON.stringify(menus));
  }

  public getMenus(): string {
    return window.sessionStorage.getItem(MENU_KEY);
  }
}
