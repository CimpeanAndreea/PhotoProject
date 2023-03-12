import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrollPhotoshootComponent } from './enroll-photoshoot.component';

describe('EnrollPhotoshootComponent', () => {
  let component: EnrollPhotoshootComponent;
  let fixture: ComponentFixture<EnrollPhotoshootComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnrollPhotoshootComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnrollPhotoshootComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
