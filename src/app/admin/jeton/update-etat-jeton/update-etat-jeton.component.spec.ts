import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateEtatJetonComponent } from './update-etat-jeton.component';

describe('UpdateEtatJetonComponent', () => {
  let component: UpdateEtatJetonComponent;
  let fixture: ComponentFixture<UpdateEtatJetonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateEtatJetonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateEtatJetonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
