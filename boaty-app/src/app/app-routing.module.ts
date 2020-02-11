import { ViewLinesComponent } from './lines/view-lines/view-lines.component';
import { AddLineComponent } from './lines/add-line/add-line.component';
import { AddZoneComponent } from './zones/add-zone/add-zone.component';
import { ViewZonesComponent } from './zones/view-zones/view-zones.component';
import { ViewStationsComponent } from './stations/view-stations/view-stations.component';
import { AddStationComponent } from './stations/add-station/add-station.component';
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
  { path: 'admin/tickets/add', component: AddTicketComponent},//, canActivate: [AuthGuard], data: {allowedRoles: ["ADMIN"]} }
  { path: 'admin/tickets', component: AddTicketComponent},
  { path: 'admin/stations/add', component: AddStationComponent},
  { path: 'admin/stations', component: ViewStationsComponent},
  { path: 'admin/lines/add', component: AddLineComponent },
  { path: 'admin/lines', component: ViewLinesComponent },
  { path: 'admin/zones', component: ViewZonesComponent},
  { path: 'admin/zones/add', component: AddZoneComponent},
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
