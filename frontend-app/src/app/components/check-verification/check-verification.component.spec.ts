import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckVerificationComponent } from './check-verification.component';

describe('CheckVerificationComponent', () => {
  let component: CheckVerificationComponent;
  let fixture: ComponentFixture<CheckVerificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CheckVerificationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CheckVerificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
