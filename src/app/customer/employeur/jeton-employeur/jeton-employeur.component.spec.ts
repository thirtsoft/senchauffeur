import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JetonEmployeurComponent } from './jeton-employeur.component';

describe('JetonEmployeurComponent', () => {
  let component: JetonEmployeurComponent;
  let fixture: ComponentFixture<JetonEmployeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JetonEmployeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JetonEmployeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
