import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchInCandidatesComponent } from './search-in-candidates.component';

describe('SearchInCandidatesComponent', () => {
  let component: SearchInCandidatesComponent;
  let fixture: ComponentFixture<SearchInCandidatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchInCandidatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchInCandidatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
