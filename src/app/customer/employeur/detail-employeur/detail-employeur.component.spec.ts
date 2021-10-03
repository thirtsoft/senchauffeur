import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailEmployeurComponent } from './detail-employeur.component';

describe('DetailEmployeurComponent', () => {
  let component: DetailEmployeurComponent;
  let fixture: ComponentFixture<DetailEmployeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailEmployeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailEmployeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
