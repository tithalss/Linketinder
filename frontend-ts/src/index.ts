// src/app.ts

import { createLoginForm } from './components/login';

document.addEventListener('DOMContentLoaded', () => {
    const app = document.querySelector('body');
    if (app) {
        app.appendChild(createLoginForm());
    }
});
