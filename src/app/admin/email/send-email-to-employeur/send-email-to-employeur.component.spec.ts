import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SendEmailToEmployeurComponent } from './send-email-to-employeur.component';

describe('SendEmailToEmployeurComponent', () => {
  let component: SendEmailToEmployeurComponent;
  let fixture: ComponentFixture<SendEmailToEmployeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SendEmailToEmployeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SendEmailToEmployeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
