import { Component, OnInit } from '@angular/core';
import { CheckService, Check } from '../../services/check.service';

@Component({
  selector: 'app-check-history',
  template: `
    <div class="container mt-4">
      <h2>Check Verification History</h2>
      <div class="mb-3">
        <input type="text" class="form-control" [(ngModel)]="accountNumber" placeholder="Enter Account Number">
        <button class="btn btn-primary mt-2" (click)="loadHistory()">Load History</button>
      </div>

      <div class="table-responsive" *ngIf="checks.length > 0">
        <table class="table table-striped">
          <thead>
            <tr>
              <th>Check Number</th>
              <th>Amount</th>
              <th>Status</th>
              <th>Timestamp</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let check of checks">
              <td>{{check.checkNumber}}</td>
              <td>{{check.amount | currency}}</td>
              <td>
                <span class="badge" [ngClass]="{'bg-success': check.status === 'VALID', 'bg-danger': check.status === 'INVALID'}">
                  {{check.status}}
                </span>
              </td>
              <td>{{check.timestamp | date:'medium'}}</td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="alert alert-info" *ngIf="checks.length === 0">
        No check history found for this account.
      </div>
    </div>
  `,
  styles: []
})
export class CheckHistoryComponent implements OnInit {
  accountNumber: string = '';
  checks: Check[] = [];

  constructor(private checkService: CheckService) {}

  ngOnInit(): void {}

  loadHistory(): void {
    if (this.accountNumber) {
      this.checkService.getCheckHistory(this.accountNumber)
        .subscribe({
          next: (history) => {
            this.checks = history;
          },
          error: (error) => {
            console.error('Error loading check history:', error);
            // Handle error appropriately
          }
        });
    }
  }
} 