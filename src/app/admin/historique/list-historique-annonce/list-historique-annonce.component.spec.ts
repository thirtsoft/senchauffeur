import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListHistoriqueAnnonceComponent } from './list-historique-annonce.component';

describe('ListHistoriqueAnnonceComponent', () => {
  let component: ListHistoriqueAnnonceComponent;
  let fixture: ComponentFixture<ListHistoriqueAnnonceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListHistoriqueAnnonceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListHistoriqueAnnonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
