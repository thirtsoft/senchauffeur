import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAnnonceComponent } from './view-annonce.component';

describe('ViewAnnonceComponent', () => {
  let component: ViewAnnonceComponent;
  let fixture: ComponentFixture<ViewAnnonceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAnnonceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAnnonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
