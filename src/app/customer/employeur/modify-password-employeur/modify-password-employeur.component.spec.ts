import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyPasswordEmployeurComponent } from './modify-password-employeur.component';

describe('ModifyPasswordEmployeurComponent', () => {
  let component: ModifyPasswordEmployeurComponent;
  let fixture: ComponentFixture<ModifyPasswordEmployeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModifyPasswordEmployeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyPasswordEmployeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
