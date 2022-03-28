import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResponseMailComponent } from './response-mail.component';

describe('ResponseMailComponent', () => {
  let component: ResponseMailComponent;
  let fixture: ComponentFixture<ResponseMailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResponseMailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResponseMailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
