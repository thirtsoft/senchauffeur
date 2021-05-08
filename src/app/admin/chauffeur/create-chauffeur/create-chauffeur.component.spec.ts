import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateChauffeurComponent } from './create-chauffeur.component';

describe('CreateChauffeurComponent', () => {
  let component: CreateChauffeurComponent;
  let fixture: ComponentFixture<CreateChauffeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateChauffeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateChauffeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
