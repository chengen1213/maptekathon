import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PoppverBasicComponent } from './poppver-basic.component';

describe('PoppverBasicComponent', () => {
  let component: PoppverBasicComponent;
  let fixture: ComponentFixture<PoppverBasicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PoppverBasicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PoppverBasicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
