import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Top3RatingToChauffeurComponent } from './top3-rating-to-chauffeur.component';

describe('Top3RatingToChauffeurComponent', () => {
  let component: Top3RatingToChauffeurComponent;
  let fixture: ComponentFixture<Top3RatingToChauffeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Top3RatingToChauffeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Top3RatingToChauffeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
