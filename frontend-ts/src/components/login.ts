function createLoginForm(): HTMLElement {
    const container = document.createElement('div');
    container.classList.add('login-container');

    const logo = document.createElement('img');
    logo.src = '/Linketinder/frontend-ts/public/assets/Ltlogo.png';
    logo.alt = 'Logo Linketinder';
    logo.classList.add('login-logo');

    const usernameInput = document.createElement('input');
    usernameInput.type = 'text';
    usernameInput.placeholder = 'EMAIL';

    const passwordInput = document.createElement('input');
    passwordInput.type = 'password';
    passwordInput.placeholder = 'SENHA';

    const loginButton = document.createElement('button');
    loginButton.textContent = 'Entrar';

    const registerAsCompanyButton = document.createElement('a');
    registerAsCompanyButton.textContent = 'Cadastrar como Empresa';
    registerAsCompanyButton.href = 'formularioEmpresa.html';

    const registerAsCandidateButton = document.createElement('a');
    registerAsCandidateButton.textContent = 'Cadastrar como Candidato';
    registerAsCandidateButton.href = 'formularioCandidato.html';

    container.appendChild(logo);
    container.appendChild(usernameInput);
    container.appendChild(passwordInput);
    container.appendChild(loginButton);

    const registerContainer = document.createElement('div');
    registerContainer.classList.add('register-container');
    registerContainer.appendChild(registerAsCompanyButton);
    registerContainer.appendChild(registerAsCandidateButton);

    container.appendChild(registerContainer);

    return container;
}

document.addEventListener('DOMContentLoaded', () => {
    const app = document.querySelector('body');
    if (app) {
        app.appendChild(createLoginForm());
    }
});
