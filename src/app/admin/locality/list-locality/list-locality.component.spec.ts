import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListLocalityComponent } from './list-locality.component';

describe('ListLocalityComponent', () => {
  let component: ListLocalityComponent;
  let fixture: ComponentFixture<ListLocalityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListLocalityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListLocalityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
