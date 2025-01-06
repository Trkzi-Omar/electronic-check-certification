import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Check, VerificationRequest, VerificationResponse } from '../models/check.model';

@Injectable({
  providedIn: 'root'
})
export class CheckService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  verifyCheck(request: VerificationRequest): Observable<VerificationResponse> {
    return this.http.post<VerificationResponse>(
      `${this.apiUrl}${environment.endpoints.checkVerification}`,
      request
    );
  }

  getCheckHistory(): Observable<Check[]> {
    return this.http.get<Check[]>(
      `${this.apiUrl}${environment.endpoints.checkHistory}`
    );
  }

  getAnalytics() {
    return this.http.get(
      `${this.apiUrl}${environment.endpoints.analytics}`
    );
  }
} 