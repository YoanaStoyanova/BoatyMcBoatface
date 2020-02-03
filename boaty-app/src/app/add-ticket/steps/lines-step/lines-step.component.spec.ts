import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LinesStepComponent } from './lines-step.component';

describe('LinesStepComponent', () => {
  let component: LinesStepComponent;
  let fixture: ComponentFixture<LinesStepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LinesStepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LinesStepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
