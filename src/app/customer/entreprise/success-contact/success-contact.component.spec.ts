import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessContactComponent } from './success-contact.component';

describe('SuccessContactComponent', () => {
  let component: SuccessContactComponent;
  let fixture: ComponentFixture<SuccessContactComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuccessContactComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
