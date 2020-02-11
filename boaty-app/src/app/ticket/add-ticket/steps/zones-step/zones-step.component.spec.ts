import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ZonesStepComponent } from './zones-step.component';

describe('ZonesStepComponent', () => {
  let component: ZonesStepComponent;
  let fixture: ComponentFixture<ZonesStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ZonesStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ZonesStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
