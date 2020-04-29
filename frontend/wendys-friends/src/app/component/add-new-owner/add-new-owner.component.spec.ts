import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewOwnerComponent } from './add-new-owner.component';

describe('AddNewOwnerComponent', () => {
  let component: AddNewOwnerComponent;
  let fixture: ComponentFixture<AddNewOwnerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNewOwnerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
