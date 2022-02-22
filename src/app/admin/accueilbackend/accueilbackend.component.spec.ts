import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccueilbackendComponent } from './accueilbackend.component';

describe('AccueilbackendComponent', () => {
  let component: AccueilbackendComponent;
  let fixture: ComponentFixture<AccueilbackendComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccueilbackendComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccueilbackendComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
