import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  title = 'boatyApp';
  showSideMenu = true;
  sideMenyIconClass = "fa-angle-double-left";
  toggled = '';
  isCollapsed = true;

  public toggleSideMenu() {
    console.log("toggling side menu");
    this.showSideMenu = !this.showSideMenu;
    this.toggled = this.toggled == '' ? 'toggled' : '';
    this.sideMenyIconClass = this.sideMenyIconClass === "fa-angle-double-left" ? "fa-angle-double-right" : "fa-angle-double-left";
  }
  
}