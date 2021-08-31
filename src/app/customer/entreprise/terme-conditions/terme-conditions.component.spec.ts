import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TermeConditionsComponent } from './terme-conditions.component';

describe('TermeConditionsComponent', () => {
  let component: TermeConditionsComponent;
  let fixture: ComponentFixture<TermeConditionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TermeConditionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TermeConditionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
