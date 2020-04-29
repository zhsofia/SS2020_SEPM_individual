import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchResElementHorseComponent } from './search-res-element-horse.component';

describe('SearchResElementHorseComponent', () => {
  let component: SearchResElementHorseComponent;
  let fixture: ComponentFixture<SearchResElementHorseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchResElementHorseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchResElementHorseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
