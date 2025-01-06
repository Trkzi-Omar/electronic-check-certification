export interface Check {
  id?: string;
  checkNumber: string;
  amount: number;
  issuanceDate: Date;
  bankAccountId: string;
  merchantId: string;
  status: CheckStatus;
  verificationDate?: Date;
}

export enum CheckStatus {
  PENDING = 'PENDING',
  VERIFIED = 'VERIFIED',
  REJECTED = 'REJECTED'
}

export interface VerificationRequest {
  checkNumber: string;
  amount: number;
  merchantId: string;
}

export interface VerificationResponse {
  status: CheckStatus;
  message: string;
  check?: Check;
} 