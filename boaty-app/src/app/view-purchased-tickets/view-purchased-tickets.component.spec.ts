import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPurchasedTicketsComponent } from './view-purchased-tickets.component';

describe('ViewPurchasedTicketsComponent', () => {
  let component: ViewPurchasedTicketsComponent;
  let fixture: ComponentFixture<ViewPurchasedTicketsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewPurchasedTicketsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPurchasedTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
