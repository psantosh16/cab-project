import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
    selector: 'app-dashboard',
    imports: [CommonModule, RouterModule],
    templateUrl: './dashboard.html'
})
export class Dashboard {
    userName = 'John Doe';
    userEmail = 'john.doe@company.com';
    logoImage = '/logo.png';

    constructor(private router: Router) {
    }

    onLogout(): void {
        this.router.navigate(['/login']);
    }
}
