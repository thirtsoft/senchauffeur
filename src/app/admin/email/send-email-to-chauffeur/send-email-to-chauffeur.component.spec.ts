import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SendEmailToChauffeurComponent } from './send-email-to-chauffeur.component';

describe('SendEmailToChauffeurComponent', () => {
  let component: SendEmailToChauffeurComponent;
  let fixture: ComponentFixture<SendEmailToChauffeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SendEmailToChauffeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SendEmailToChauffeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
