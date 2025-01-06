import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

export interface Check {
  id: string;
  amount: number;
  accountNumber: string;
  status: string;
  checkNumber: string;
  timestamp: Date;
}

@Injectable({
  providedIn: 'root'
})
export class CheckService {
  constructor(private apiService: ApiService) { }

  verifyCheck(checkData: Partial<Check>): Observable<Check> {
    return this.apiService.post<Check>('/checks/verify', checkData);
  }

  getCheckHistory(accountNumber: string): Observable<Check[]> {
    return this.apiService.get<Check[]>(`/checks/history/${accountNumber}`);
  }

  getCheckStatus(checkNumber: string): Observable<Check> {
    return this.apiService.get<Check>(`/checks/status/${checkNumber}`);
  }
} 