import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyInfoEmployeurComponent } from './modify-info-employeur.component';

describe('ModifyInfoEmployeurComponent', () => {
  let component: ModifyInfoEmployeurComponent;
  let fixture: ComponentFixture<ModifyInfoEmployeurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModifyInfoEmployeurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyInfoEmployeurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
