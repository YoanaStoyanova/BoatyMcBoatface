import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {first} from "rxjs/operators";
import {CardPaymentMethodService} from "../services/card.payment.method.service";
import {CardPaymentMethodModel} from "../model/card.payment.method-model";
import {UserModel} from "../model/user-model";
import {UserService} from "../services/user.service";
import {AddCardModel} from "../model/add.card-model";

@Component({
  selector: 'app-add-payment',
  templateUrl: './add-payment.component.html',
  styleUrls: ['./add-payment.component.css']
})
export class AddPaymentComponent implements OnInit {
  addCardForm :FormGroup;
  user :UserModel;

  constructor(
      private router :Router,
      private route :ActivatedRoute,
      private formBuilder :FormBuilder,
      private paymentService :CardPaymentMethodService,
      private userService :UserService
  ) {
    userService.getCurrent().subscribe(user => this.user = user);
  }


  ngOnInit(): void {
    this.addCardForm = this.formBuilder.group({
      cardNumber: ['', Validators.compose([Validators.required, Validators.minLength(13), Validators.maxLength(16)])],
      cardHolder: ['', Validators.required],
      cardValidity: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.addCardForm.invalid) {
      return;
    }

    let cardNumber = +this.addCardForm.controls.cardNumber.value;
    let card = new AddCardModel(cardNumber,
        this.addCardForm.controls.cardHolder.value,
        this.addCardForm.controls.cardValidity.value);
    this.paymentService.addCard(this.user.id, card)
        .subscribe(data => {
          this.router.navigate(["/payments"]);
        }, error => { });
  }


}
