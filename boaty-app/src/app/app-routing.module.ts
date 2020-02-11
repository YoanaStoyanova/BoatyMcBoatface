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

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: RegisterComponent},
  { path: 'admin/tickets/add', component: AddTicketComponent},//, canActivate: [AuthGuard], data: {allowedRoles: ["ADMIN"]} }
  { path: 'admin/tickets', component: AddTicketComponent},
  { path: 'admin/stations/add', component: AddStationComponent},
  { path: 'admin/stations', component: ViewStationsComponent},
  { path: 'admin/lines', component: AddTicketComponent},
  { path: 'admin/zones', component: ViewZonesComponent},
  { path: 'admin/zones/add', component: AddZoneComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
