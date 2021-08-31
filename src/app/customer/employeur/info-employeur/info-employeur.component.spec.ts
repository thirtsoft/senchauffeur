import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoEmployeurComponent } from './info-employeur.component';

describe('InfoEmployeurComponent', () => {
  let component: InfoEmployeurComponent;
  let fixture: ComponentFixture<InfoEmployeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoEmployeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoEmployeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
