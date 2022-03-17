import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCvChauffeurComponent } from './update-cv-chauffeur.component';

describe('UpdateCvChauffeurComponent', () => {
  let component: UpdateCvChauffeurComponent;
  let fixture: ComponentFixture<UpdateCvChauffeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateCvChauffeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateCvChauffeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
