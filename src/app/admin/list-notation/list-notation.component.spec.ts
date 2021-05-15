import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListNotationComponent } from './list-notation.component';

describe('ListNotationComponent', () => {
  let component: ListNotationComponent;
  let fixture: ComponentFixture<ListNotationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListNotationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListNotationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
