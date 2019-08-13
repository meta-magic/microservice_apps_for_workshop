import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderslibComponent } from './orderslib.component';

describe('OrderslibComponent', () => {
  let component: OrderslibComponent;
  let fixture: ComponentFixture<OrderslibComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderslibComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderslibComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
