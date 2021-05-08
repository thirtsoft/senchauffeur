import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListRecruteurComponent } from './list-recruteur.component';

describe('ListRecruteurComponent', () => {
  let component: ListRecruteurComponent;
  let fixture: ComponentFixture<ListRecruteurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListRecruteurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListRecruteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
