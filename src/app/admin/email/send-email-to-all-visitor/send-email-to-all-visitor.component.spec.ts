import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SendEmailToAllVisitorComponent } from './send-email-to-all-visitor.component';

describe('SendEmailToAllVisitorComponent', () => {
  let component: SendEmailToAllVisitorComponent;
  let fixture: ComponentFixture<SendEmailToAllVisitorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SendEmailToAllVisitorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SendEmailToAllVisitorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
