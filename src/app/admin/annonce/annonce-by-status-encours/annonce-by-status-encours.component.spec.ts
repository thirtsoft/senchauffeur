import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnonceByStatusEncoursComponent } from './annonce-by-status-encours.component';

describe('AnnonceByStatusEncoursComponent', () => {
  let component: AnnonceByStatusEncoursComponent;
  let fixture: ComponentFixture<AnnonceByStatusEncoursComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnnonceByStatusEncoursComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnonceByStatusEncoursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
