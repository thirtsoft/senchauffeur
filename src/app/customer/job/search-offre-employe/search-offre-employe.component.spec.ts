import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchOffreEmployeComponent } from './search-offre-employe.component';

describe('SearchOffreEmployeComponent', () => {
  let component: SearchOffreEmployeComponent;
  let fixture: ComponentFixture<SearchOffreEmployeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchOffreEmployeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchOffreEmployeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
