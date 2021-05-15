import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateLocalityComponent } from './create-locality.component';

describe('CreateLocalityComponent', () => {
  let component: CreateLocalityComponent;
  let fixture: ComponentFixture<CreateLocalityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateLocalityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateLocalityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
