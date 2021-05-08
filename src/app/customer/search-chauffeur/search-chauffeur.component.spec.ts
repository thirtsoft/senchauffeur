import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchChauffeurComponent } from './search-chauffeur.component';

describe('SearchChauffeurComponent', () => {
  let component: SearchChauffeurComponent;
  let fixture: ComponentFixture<SearchChauffeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchChauffeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchChauffeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
