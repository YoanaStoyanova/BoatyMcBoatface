import {Component, OnDestroy, OnInit} from '@angular/core';
import {CardPaymentMethodService} from "../services/card.payment.method.service";
import {CardPaymentMethodModel} from "../model/card.payment.method-model";
import {UserService} from "../services/user.service";
import {PagedResponseModel} from "../model/paged.response-model";
import {NavigationEnd, Router, RouterEvent} from "@angular/router";
import {filter, first, takeUntil} from "rxjs/operators";
import {Subject} from "rxjs";

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit, OnDestroy {

    public destroyed = new Subject<any>();
    cardPage: PagedResponseModel<CardPaymentMethodModel>;

  constructor(private router :Router,
              private cardPaymentMethodService: CardPaymentMethodService,
              private userService: UserService) { }

  ngOnInit() {
      this.fetchCards();

      this.router.events.pipe(
          filter((event: RouterEvent) => event instanceof NavigationEnd),
          takeUntil(this.destroyed)
      ).subscribe(() => {
          this.fetchCards();
      });
  }

  ngOnDestroy(): void {
    this.destroyed.next();
    this.destroyed.complete();
  }

  deleteCard(cardId :number) {
      this.cardPaymentMethodService.deleteCard(cardId)
          .subscribe(data => {
              this.router.navigate(["/payments"]);
          }, error => { });
  }

  private fetchCards() {
      this.userService.getCurrent().subscribe(user => {
          this.cardPaymentMethodService.listCards(user.id)
              .subscribe(page => {
                  this.cardPage = page;
              });
      });
  }

}
