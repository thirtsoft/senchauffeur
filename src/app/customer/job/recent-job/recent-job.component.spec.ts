import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecentJobComponent } from './recent-job.component';

describe('RecentJobComponent', () => {
  let component: RecentJobComponent;
  let fixture: ComponentFixture<RecentJobComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecentJobComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecentJobComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
