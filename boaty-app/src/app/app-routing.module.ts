import { ViewTicketComponent } from './ticket/view-ticket/view-ticket.component';
import { ViewLinesComponent } from './lines/view-lines/view-lines.component';
import { AddLineComponent } from './lines/add-line/add-line.component';
import { AddZoneComponent } from './zones/add-zone/add-zone.component';
import { ViewZonesComponent } from './zones/view-zones/view-zones.component';
import { ViewStationsComponent } from './stations/view-stations/view-stations.component';
import { AddStationComponent } from './stations/add-station/add-station.component';
import {Component, NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {AddTicketComponent} from "./ticket/add-ticket/add-ticket.component";
import {AuthGuard} from "./guards/auth.guard";
import {RegisterComponent} from "./register/register.component";
import {PaymentsComponent} from "./payments/payments.component";
import {AddPaymentComponent} from "./add-payment/add-payment.component";
import {HomeComponent} from "./home/home.component";
import {ViewPurchasedTicketsComponent} from "./view-purchased-tickets/view-purchased-tickets.component";

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'signup', component: RegisterComponent},
  { path: 'admin/tickets/add', component: AddTicketComponent, canActivate: [AuthGuard], data: {allowedRoles: ["ADMIN"]}},
  { path: 'purchased-tickets', component: ViewPurchasedTicketsComponent, canActivate: [AuthGuard], data: {allowedRoles: ["USER", "ADMIN"]}},
  { path: 'tickets', component: ViewTicketComponent, canActivate: [AuthGuard], data: {allowedRoles: ["USER", "ADMIN"]}},
  { path: 'admin/stations/add', component: AddStationComponent, canActivate: [AuthGuard], data: {allowedRoles: ["ADMIN"]}},
  { path: 'admin/stations', component: ViewStationsComponent, canActivate: [AuthGuard], data: {allowedRoles: ["ADMIN"]}},
  { path: 'admin/lines/add', component: AddLineComponent, canActivate: [AuthGuard], data: {allowedRoles: ["ADMIN"]}},
  { path: 'admin/lines', component: ViewLinesComponent, canActivate: [AuthGuard], data: {allowedRoles: ["ADMIN"]}},
  { path: 'admin/zones', component: ViewZonesComponent, canActivate: [AuthGuard], data: {allowedRoles: ["ADMIN"]}},
  { path: 'admin/zones/add', component: AddZoneComponent, canActivate: [AuthGuard], data: {allowedRoles: ["ADMIN"]}},
  { path: 'admin/tickets', component: AddTicketComponent, canActivate: [AuthGuard], data: {allowedRoles: ["ADMIN"]}},
  { path: 'payments', component: PaymentsComponent, canActivate: [AuthGuard], data: {allowedRoles: ["USER", "ADMIN"]}},
  { path: 'payments/new', component: AddPaymentComponent, canActivate: [AuthGuard], data: {allowedRoles: ["USER", "ADMIN"]}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    onSameUrlNavigation: "reload"
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
