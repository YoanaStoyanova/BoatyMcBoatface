import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {first} from "rxjs/operators";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication.service";
import {CustomValidators} from "../custom-validators";
import {SignupModel} from "../model/signup-model";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  signupForm :FormGroup;

  constructor(
      private router :Router,
      private route :ActivatedRoute,
      private formBuilder :FormBuilder,
      private userService :UserService,
      private authenticationService :AuthenticationService
  ) {
    if (this.authenticationService.isAuthenticated()) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {
    this.signupForm = this.formBuilder.group({
      username: ['', Validators.compose(
          [Validators.required, Validators.email])
      ],
      password: ['', Validators.compose(
          [Validators.required, Validators.minLength(8)])
      ],
      repeatedPassword: ['', Validators.compose(
          [Validators.required, CustomValidators.matchOtherValidator('password')])
      ],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.signupForm.invalid) {
      return;
    }

    let username = this.signupForm.controls.username.value;
    let password = this.signupForm.controls.password.value;
    let firstName = this.signupForm.controls.firstName.value;
    let lastName = this.signupForm.controls.lastName.value;
    let user = new SignupModel(username, password, firstName, lastName);

    this.userService.createUser(user)
        .pipe(first())
        .subscribe(
            data => {
              this.router.navigate(['/login']);
            },
            error => {
            });
  }
}
