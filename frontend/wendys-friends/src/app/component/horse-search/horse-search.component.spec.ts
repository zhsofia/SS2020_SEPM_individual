import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HorseSearchComponent } from './horse-search.component';

describe('HorseSearchComponent', () => {
  let component: HorseSearchComponent;
  let fixture: ComponentFixture<HorseSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HorseSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HorseSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
