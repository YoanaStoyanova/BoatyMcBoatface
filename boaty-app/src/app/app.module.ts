import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AddTicketComponent } from './add-ticket/add-ticket.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NameStepComponent } from './add-ticket/steps/name-step/name-step.component';
import { ZonesStepComponent } from './add-ticket/steps/zones-step/zones-step.component';
import { LinesStepComponent } from './add-ticket/steps/lines-step/lines-step.component';
import { TransportTypeStepComponent } from './add-ticket/steps/transport-type-step/transport-type-step.component';
import { AdditionalLinesStepComponent } from './add-ticket/steps/additional-lines-step/additional-lines-step.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from "@angular/common/http";
import {JwtModule} from "@auth0/angular-jwt";
import {AuthorizationService} from "./services/authorization.service";
import { RegisterComponent } from './register/register.component';
import { AddStationComponent } from './stations/add-station/add-station.component';
import { ViewStationsComponent } from './stations/view-stations/view-stations.component';
import { AddZoneComponent } from './zones/add-zone/add-zone.component';
import { ViewZonesComponent } from './zones/view-zones/view-zones.component';
import { PaymentsComponent } from './payments/payments.component';
import { AddPaymentComponent } from './add-payment/add-payment.component';

export function tokenGetter() {

  let token = localStorage.getItem('token');
  if (token != null) {
    return JSON.parse(token)["token"];
  }
  return null;
}

@NgModule({
  declarations: [
    AppComponent,
    AddTicketComponent,
    NameStepComponent,
    ZonesStepComponent,
    LinesStepComponent,
    TransportTypeStepComponent,
    AdditionalLinesStepComponent,
    LoginComponent,
    RegisterComponent,
    AddStationComponent,
    ViewStationsComponent,
    AddZoneComponent,
    ViewZonesComponent,
    RegisterComponent,
    PaymentsComponent,
    AddPaymentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: ["localhost:8080"],
        blacklistedRoutes: ["localhost:8080/api/tokens"]
      }
    })
  ],
  bootstrap: [AppComponent],
  entryComponents:[
    AdditionalLinesStepComponent
  ],
  providers: [AuthorizationService]
})
export class AppModule { }
