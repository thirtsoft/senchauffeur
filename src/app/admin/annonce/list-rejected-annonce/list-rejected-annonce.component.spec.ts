import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListRejectedAnnonceComponent } from './list-rejected-annonce.component';

describe('ListRejectedAnnonceComponent', () => {
  let component: ListRejectedAnnonceComponent;
  let fixture: ComponentFixture<ListRejectedAnnonceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListRejectedAnnonceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListRejectedAnnonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
