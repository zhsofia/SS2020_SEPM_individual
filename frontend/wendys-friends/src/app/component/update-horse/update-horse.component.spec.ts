import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateHorseComponent } from './update-horse.component';

describe('UpdateHorseComponent', () => {
  let component: UpdateHorseComponent;
  let fixture: ComponentFixture<UpdateHorseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateHorseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateHorseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
