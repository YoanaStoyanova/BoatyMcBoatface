import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewZonesComponent } from './view-zones.component';

describe('ViewZonesComponent', () => {
  let component: ViewZonesComponent;
  let fixture: ComponentFixture<ViewZonesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewZonesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewZonesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
