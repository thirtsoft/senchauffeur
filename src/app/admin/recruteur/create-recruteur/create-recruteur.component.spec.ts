import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateRecruteurComponent } from './create-recruteur.component';

describe('CreateRecruteurComponent', () => {
  let component: CreateRecruteurComponent;
  let fixture: ComponentFixture<CreateRecruteurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateRecruteurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateRecruteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
