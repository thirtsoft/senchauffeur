import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewChauffeurComponent } from './view-chauffeur.component';

describe('ViewChauffeurComponent', () => {
  let component: ViewChauffeurComponent;
  let fixture: ComponentFixture<ViewChauffeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewChauffeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewChauffeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
