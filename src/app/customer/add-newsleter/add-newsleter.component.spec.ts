import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewsleterComponent } from './add-newsleter.component';

describe('AddNewsleterComponent', () => {
  let component: AddNewsleterComponent;
  let fixture: ComponentFixture<AddNewsleterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNewsleterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewsleterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
