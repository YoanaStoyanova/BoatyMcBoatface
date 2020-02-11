import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "./services/authentication.service";
import {UserModel} from "./model/user-model";
import {UserService} from "./services/user.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  currentUser :Observable<UserModel>;
  title = 'boatyApp';
  showSideMenu = true;
  sideMenuIconClass = "fa-angle-double-left";
  toggled = '';

  constructor(private authService :AuthenticationService,
              private userService :UserService) {
  }

  public toggleSideMenu() {
    this.showSideMenu = !this.showSideMenu;
    this.toggled = this.toggled == '' ? 'toggled' : '';
    this.sideMenuIconClass = this.sideMenuIconClass === "fa-angle-double-left" ? "fa-angle-double-right" : "fa-angle-double-left";
  }

  ngOnInit(): void {
    if (this.authService.isAuthenticated()) {
      this.currentUser = this.userService.getCurrent();
          //.subscribe(user => this.currentUser = user);
    }
  }
}