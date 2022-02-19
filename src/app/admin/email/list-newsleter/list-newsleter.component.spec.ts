import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListNewsleterComponent } from './list-newsleter.component';

describe('ListNewsleterComponent', () => {
  let component: ListNewsleterComponent;
  let fixture: ComponentFixture<ListNewsleterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListNewsleterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListNewsleterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
