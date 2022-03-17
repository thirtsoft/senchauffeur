import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePhotoChauffeurComponent } from './update-photo-chauffeur.component';

describe('UpdatePhotoChauffeurComponent', () => {
  let component: UpdatePhotoChauffeurComponent;
  let fixture: ComponentFixture<UpdatePhotoChauffeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdatePhotoChauffeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatePhotoChauffeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
