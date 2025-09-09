import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Dashboard } from './components/dashboard/dashboard';
import { SubmitChangeRequest } from './components/submit-change-request/submit-change-request';

export const routes: Routes = [
    {
        path: "", redirectTo: 'dashboard', pathMatch: "full"
    },
    {
        path: "login", component: Login
    },
    {
        path: "dashboard", component: Dashboard
    },
    {
        path: "change-request", component: SubmitChangeRequest
    }
];
