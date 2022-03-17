import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListValidatedAnnonceComponent } from './list-validated-annonce.component';

describe('ListValidatedAnnonceComponent', () => {
  let component: ListValidatedAnnonceComponent;
  let fixture: ComponentFixture<ListValidatedAnnonceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListValidatedAnnonceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListValidatedAnnonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
