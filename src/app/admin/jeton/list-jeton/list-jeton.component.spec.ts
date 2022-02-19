import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListJetonComponent } from './list-jeton.component';

describe('ListJetonComponent', () => {
  let component: ListJetonComponent;
  let fixture: ComponentFixture<ListJetonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListJetonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListJetonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
