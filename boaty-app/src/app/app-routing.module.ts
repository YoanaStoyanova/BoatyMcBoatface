import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {AddTicketComponent} from "./add-ticket/add-ticket.component";
import {AuthGuard} from "./guards/auth.guard";
import {RegisterComponent} from "./register/register.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: RegisterComponent},
  { path: 'admin/tickets', component: AddTicketComponent, canActivate: [AuthGuard], data: {allowedRoles: ["ADMIN"]} }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
