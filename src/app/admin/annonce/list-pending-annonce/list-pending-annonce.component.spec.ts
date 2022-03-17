import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPendingAnnonceComponent } from './list-pending-annonce.component';

describe('ListPendingAnnonceComponent', () => {
  let component: ListPendingAnnonceComponent;
  let fixture: ComponentFixture<ListPendingAnnonceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListPendingAnnonceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListPendingAnnonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
