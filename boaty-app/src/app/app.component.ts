import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthenticationService} from "./services/authentication.service";
import {UserModel} from "./model/user-model";
import {UserService} from "./services/user.service";
import {Observable, Subject} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NavigationEnd, Router, RouterEvent} from "@angular/router";
import {TopUpModel} from "./model/top.up-model";
import {filter, takeUntil} from "rxjs/operators";
import {CardPaymentMethodService} from "./services/card.payment.method.service";
import {PagedResponseModel} from "./model/paged.response-model";
import {CardPaymentMethodModel} from "./model/card.payment.method-model";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit, OnDestroy {

  private destroyed = new Subject<any>();
  private cardPage :Observable<PagedResponseModel<CardPaymentMethodModel>>;
  topUpForm :FormGroup;
  currentUser :Observable<UserModel>;
  title = 'boatyApp';
  showSideMenu = true;
  sideMenuIconClass = "fa-angle-double-left";
  toggled = '';

  constructor(private router :Router,
              private formBuilder :FormBuilder,
              private authService :AuthenticationService,
              private paymentMethodService :CardPaymentMethodService,
              private userService :UserService,
              private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.topUpForm = this.formBuilder.group({
      card: ['', Validators.required],
      amount: ['', Validators.compose([Validators.required, Validators.min(20), Validators.max(100)])]
    });

    this.router.events.pipe(
        filter((event: RouterEvent) => event instanceof NavigationEnd),
        takeUntil(this.destroyed))
        .subscribe(() => {
          if (this.authService.isAuthenticated()) {
              this.fetchUser();
              this.fetchCards();
          }
        });
  }

  ngOnDestroy(): void {
    this.destroyed.next();
    this.destroyed.complete();
  }

  public toggleSideMenu() {
    this.showSideMenu = !this.showSideMenu;
    this.toggled = this.toggled == '' ? 'toggled' : '';
    this.sideMenuIconClass = this.sideMenuIconClass === "fa-angle-double-left" ? "fa-angle-double-right" : "fa-angle-double-left";
  }

  onTopUp() {
    if (this.topUpForm.invalid) {
      return;
    }

    let card = this.topUpForm.controls.card.value;
    let amount = this.topUpForm.controls.amount.value;
    this.currentUser.subscribe(user => {
      this.userService.topUpAccount(user.id, new TopUpModel("CARD", card, amount))
          .subscribe(success => this.fetchUser());
    });
  }

  private fetchUser() {
    this.currentUser = this.userService.getCurrent();
    this.currentUser.subscribe();
  }

  private fetchCards() {
    this.userService.getCurrent().subscribe(user => {
      this.cardPage = this.paymentMethodService.listCards(user.id);
    });
  }

}
