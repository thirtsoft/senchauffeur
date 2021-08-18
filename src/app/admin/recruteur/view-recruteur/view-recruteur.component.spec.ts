import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRecruteurComponent } from './view-recruteur.component';

describe('ViewRecruteurComponent', () => {
  let component: ViewRecruteurComponent;
  let fixture: ComponentFixture<ViewRecruteurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewRecruteurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewRecruteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
