import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SendEmailToNewsleterComponent } from './send-email-to-newsleter.component';

describe('SendEmailToNewsleterComponent', () => {
  let component: SendEmailToNewsleterComponent;
  let fixture: ComponentFixture<SendEmailToNewsleterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SendEmailToNewsleterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SendEmailToNewsleterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
