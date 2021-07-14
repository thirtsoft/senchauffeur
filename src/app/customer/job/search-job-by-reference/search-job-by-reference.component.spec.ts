import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchJobByReferenceComponent } from './search-job-by-reference.component';

describe('SearchJobByReferenceComponent', () => {
  let component: SearchJobByReferenceComponent;
  let fixture: ComponentFixture<SearchJobByReferenceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchJobByReferenceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchJobByReferenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
