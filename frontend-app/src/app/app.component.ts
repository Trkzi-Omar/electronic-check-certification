import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <div class="container">
        <a class="navbar-brand" href="#">Check Certification System</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" routerLink="/verify" routerLinkActive="active">Verify Check</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" routerLink="/history" routerLinkActive="active">Check History</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <router-outlet></router-outlet>
  `,
  styles: [`
    .navbar {
      margin-bottom: 20px;
    }
    .nav-link.active {
      font-weight: bold;
    }
  `]
})
export class AppComponent {
  title = 'Check Certification System';
}
