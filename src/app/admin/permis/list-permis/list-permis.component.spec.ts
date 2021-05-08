import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPermisComponent } from './list-permis.component';

describe('ListPermisComponent', () => {
  let component: ListPermisComponent;
  let fixture: ComponentFixture<ListPermisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListPermisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListPermisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
