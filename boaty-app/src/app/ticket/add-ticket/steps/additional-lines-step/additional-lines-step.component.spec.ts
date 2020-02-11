import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdditionalLinesStepComponent } from './additional-lines-step.component';

describe('AdditionalLinesStepComponent', () => {
  let component: AdditionalLinesStepComponent;
  let fixture: ComponentFixture<AdditionalLinesStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdditionalLinesStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdditionalLinesStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
