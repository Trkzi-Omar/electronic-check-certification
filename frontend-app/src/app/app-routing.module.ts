import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CheckVerificationComponent } from './components/check-verification/check-verification.component';
import { CheckHistoryComponent } from './components/check-history/check-history.component';

const routes: Routes = [
  { path: '', redirectTo: '/verify', pathMatch: 'full' },
  { path: 'verify', component: CheckVerificationComponent },
  { path: 'history', component: CheckHistoryComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { } 