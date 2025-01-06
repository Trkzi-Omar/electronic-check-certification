import { Routes } from '@angular/router';
import { CheckVerificationComponent } from './components/check-verification/check-verification.component';
import { CheckHistoryComponent } from './components/check-history/check-history.component';

export const routes: Routes = [
  { path: '', redirectTo: '/verify', pathMatch: 'full' },
  { path: 'verify', component: CheckVerificationComponent },
  { path: 'history', component: CheckHistoryComponent },
  { path: '**', redirectTo: '/verify' }
];
