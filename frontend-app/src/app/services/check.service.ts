import { Injectable } from '@angular/core';
import { Observable, of, delay } from 'rxjs';
import { Check, VerificationRequest, VerificationResponse, CheckStatus } from '../models/check.model';

@Injectable({
  providedIn: 'root'
})
export class CheckService {
  private mockChecks: Check[] = [
    {
      id: '1',
      checkNumber: 'CHK001',
      amount: 1000,
      issuanceDate: new Date(),
      bankAccountId: 'ACC001',
      merchantId: 'MERCH001',
      status: CheckStatus.VERIFIED,
      verificationDate: new Date()
    },
    {
      id: '2',
      checkNumber: 'CHK002',
      amount: 2000,
      issuanceDate: new Date(),
      bankAccountId: 'ACC002',
      merchantId: 'MERCH001',
      status: CheckStatus.REJECTED,
      verificationDate: new Date()
    }
  ];

  constructor() {}

  verifyCheck(request: VerificationRequest): Observable<VerificationResponse> {
    // Simulate API delay
    const isValid = Math.random() > 0.3; // 70% chance of success

    const response: VerificationResponse = {
      status: isValid ? CheckStatus.VERIFIED : CheckStatus.REJECTED,
      message: isValid ? 'Check verified successfully' : 'Check verification failed: Invalid check details',
      check: isValid ? {
        id: Math.random().toString(36).substring(7),
        ...request,
        issuanceDate: new Date(),
        bankAccountId: 'ACC' + Math.floor(Math.random() * 1000),
        status: CheckStatus.VERIFIED,
        verificationDate: new Date()
      } : undefined
    };

    return of(response).pipe(delay(1000)); // Simulate 1 second delay
  }

  getCheckHistory(): Observable<Check[]> {
    return of(this.mockChecks).pipe(delay(1000));
  }

  getAnalytics() {
    const mockAnalytics = {
      totalChecks: 150,
      verifiedChecks: 120,
      rejectedChecks: 30,
      totalAmount: 450000
    };
    return of(mockAnalytics).pipe(delay(1000));
  }
} 