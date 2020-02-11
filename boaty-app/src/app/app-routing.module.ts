import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {AddTicketComponent} from "./add-ticket/add-ticket.component";
import {AuthGuard} from "./guards/auth.guard";
import {RegisterComponent} from "./register/register.component";
import {PaymentsComponent} from "./payments/payments.component";
import {AddPaymentComponent} from "./add-payment/add-payment.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: RegisterComponent},
  { path: 'payments', component: PaymentsComponent, canActivate: [AuthGuard], data: {allowedRoles: ["USER"]} },
  { path: 'payments/new', component: AddPaymentComponent, canActivate: [AuthGuard], data: {allowedRoles: ["USER"]} },
  { path: 'admin/tickets', component: AddTicketComponent, canActivate: [AuthGuard], data: {allowedRoles: ["ADMIN"]} }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    onSameUrlNavigation: "reload"
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
