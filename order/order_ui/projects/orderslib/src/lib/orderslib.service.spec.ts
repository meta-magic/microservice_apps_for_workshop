import { TestBed } from '@angular/core/testing';

import { OrderslibService } from './orderslib.service';

describe('OrderslibService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OrderslibService = TestBed.get(OrderslibService);
    expect(service).toBeTruthy();
  });
});
