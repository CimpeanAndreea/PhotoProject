import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientPhotoshootsComponent } from './client-photoshoots.component';

describe('ClientPhotoshootsComponent', () => {
  let component: ClientPhotoshootsComponent;
  let fixture: ComponentFixture<ClientPhotoshootsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientPhotoshootsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientPhotoshootsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
