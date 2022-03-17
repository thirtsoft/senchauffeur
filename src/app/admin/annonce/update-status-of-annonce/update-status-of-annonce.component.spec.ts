import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateStatusOfAnnonceComponent } from './update-status-of-annonce.component';

describe('UpdateStatusOfAnnonceComponent', () => {
  let component: UpdateStatusOfAnnonceComponent;
  let fixture: ComponentFixture<UpdateStatusOfAnnonceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateStatusOfAnnonceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateStatusOfAnnonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
