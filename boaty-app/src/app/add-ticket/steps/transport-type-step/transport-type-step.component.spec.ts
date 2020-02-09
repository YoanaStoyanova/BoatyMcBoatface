import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransportTypeStepComponent } from './transport-type-step.component';

describe('TransportTypeStepComponent', () => {
  let component: TransportTypeStepComponent;
  let fixture: ComponentFixture<TransportTypeStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransportTypeStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransportTypeStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
