import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AddTicketComponent } from './add-ticket/add-ticket.component';
import { FormsModule } from '@angular/forms';
import { NameStepComponent } from './add-ticket/steps/name-step/name-step.component';
import { ZonesStepComponent } from './add-ticket/steps/zones-step/zones-step.component';
import { LinesStepComponent } from './add-ticket/steps/lines-step/lines-step.component';
import { TransportTypeStepComponent } from './add-ticket/steps/transport-type-step/transport-type-step.component';
import { AdditionalLinesStepComponent } from './add-ticket/steps/additional-lines-step/additional-lines-step.component';

@NgModule({
  declarations: [
    AppComponent,
    AddTicketComponent,
    NameStepComponent,
    ZonesStepComponent,
    LinesStepComponent,
    TransportTypeStepComponent,
    AdditionalLinesStepComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    NgbModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents:[
    AdditionalLinesStepComponent
  ]
})
export class AppModule { }
