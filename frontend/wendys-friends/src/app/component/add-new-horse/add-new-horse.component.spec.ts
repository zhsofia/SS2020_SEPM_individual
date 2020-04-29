import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewHorseComponent } from './add-new-horse.component';

describe('AddNewHorseComponent', () => {
  let component: AddNewHorseComponent;
  let fixture: ComponentFixture<AddNewHorseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNewHorseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewHorseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
