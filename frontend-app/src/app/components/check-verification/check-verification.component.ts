import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CheckService } from '../../services/check.service';
import { VerificationRequest, VerificationResponse, CheckStatus } from '../../models/check.model';

@Component({
  selector: 'app-check-verification',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule
  ],
  templateUrl: './check-verification.component.html',
  styleUrl: './check-verification.component.scss'
})
export class CheckVerificationComponent {
  verificationForm: FormGroup;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private checkService: CheckService,
    private snackBar: MatSnackBar
  ) {
    this.verificationForm = this.fb.group({
      checkNumber: ['', [Validators.required, Validators.minLength(5)]],
      amount: ['', [Validators.required, Validators.min(0)]],
      merchantId: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.verificationForm.valid) {
      this.loading = true;
      const request: VerificationRequest = this.verificationForm.value;

      this.checkService.verifyCheck(request).subscribe({
        next: (response: VerificationResponse) => {
          const message = response.status === CheckStatus.VERIFIED
            ? 'Check verified successfully!'
            : 'Check verification failed: ' + response.message;
          
          this.snackBar.open(message, 'Close', {
            duration: 5000,
            panelClass: response.status === CheckStatus.VERIFIED ? 'success-snackbar' : 'error-snackbar'
          });

          if (response.status === CheckStatus.VERIFIED) {
            this.verificationForm.reset();
          }
        },
        error: (error) => {
          this.snackBar.open('Error verifying check: ' + error.message, 'Close', {
            duration: 5000,
            panelClass: 'error-snackbar'
          });
        },
        complete: () => {
          this.loading = false;
        }
      });
    }
  }
} 