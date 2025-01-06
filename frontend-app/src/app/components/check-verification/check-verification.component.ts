import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CheckService, Check } from '../../services/check.service';

@Component({
  selector: 'app-check-verification',
  template: `
    <div class="container mt-4">
      <h2>Check Verification</h2>
      <form [formGroup]="checkForm" (ngSubmit)="onSubmit()" class="mt-3">
        <div class="mb-3">
          <label for="checkNumber" class="form-label">Check Number</label>
          <input type="text" class="form-control" id="checkNumber" formControlName="checkNumber">
        </div>
        <div class="mb-3">
          <label for="amount" class="form-label">Amount</label>
          <input type="number" class="form-control" id="amount" formControlName="amount">
        </div>
        <div class="mb-3">
          <label for="accountNumber" class="form-label">Account Number</label>
          <input type="text" class="form-control" id="accountNumber" formControlName="accountNumber">
        </div>
        <button type="submit" class="btn btn-primary" [disabled]="!checkForm.valid">Verify Check</button>
      </form>

      <div *ngIf="verificationResult" class="mt-4">
        <h3>Verification Result</h3>
        <div class="alert" [ngClass]="{'alert-success': verificationResult.status === 'VALID', 'alert-danger': verificationResult.status === 'INVALID'}">
          Status: {{verificationResult.status}}
        </div>
      </div>
    </div>
  `,
  styles: []
})
export class CheckVerificationComponent {
  checkForm: FormGroup;
  verificationResult: Check | null = null;

  constructor(
    private fb: FormBuilder,
    private checkService: CheckService
  ) {
    this.checkForm = this.fb.group({
      checkNumber: ['', Validators.required],
      amount: ['', [Validators.required, Validators.min(0)]],
      accountNumber: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.checkForm.valid) {
      this.checkService.verifyCheck(this.checkForm.value)
        .subscribe({
          next: (result) => {
            this.verificationResult = result;
          },
          error: (error) => {
            console.error('Error verifying check:', error);
            // Handle error appropriately
          }
        });
    }
  }
} 