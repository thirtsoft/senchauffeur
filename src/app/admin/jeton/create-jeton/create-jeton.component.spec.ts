import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateJetonComponent } from './create-jeton.component';

describe('CreateJetonComponent', () => {
  let component: CreateJetonComponent;
  let fixture: ComponentFixture<CreateJetonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateJetonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateJetonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
