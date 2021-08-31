import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTarifCustomerComponent } from './list-tarif-customer.component';

describe('ListTarifCustomerComponent', () => {
  let component: ListTarifCustomerComponent;
  let fixture: ComponentFixture<ListTarifCustomerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListTarifCustomerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListTarifCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
