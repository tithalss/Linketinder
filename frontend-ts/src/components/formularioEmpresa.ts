function createCompanyForm(): HTMLElement {
    const form = document.createElement('form');
    form.classList.add('form-container');

    // Campo para Nome da Empresa
    const nomeLabel = document.createElement('label');
    nomeLabel.textContent = 'Nome da Empresa:';
    const nomeInput = document.createElement('input');
    nomeInput.type = 'text';
    nomeInput.name = 'nome';
    nomeInput.required = true;

    // Campo para Email
    const emailLabel = document.createElement('label');
    emailLabel.textContent = 'Email:';
    const emailInput = document.createElement('input');
    emailInput.type = 'email';
    emailInput.name = 'email';
    emailInput.required = true;

    // Campo para CNPJ
    const cnpjLabel = document.createElement('label');
    cnpjLabel.textContent = 'CNPJ:';
    const cnpjInput = document.createElement('input');
    cnpjInput.type = 'text';
    cnpjInput.name = 'cnpj';
    cnpjInput.required = true;

    // Campo para País
    const paisLabel = document.createElement('label');
    paisLabel.textContent = 'País:';
    const paisInput = document.createElement('input');
    paisInput.type = 'text';
    paisInput.name = 'pais';
    paisInput.required = true;

    // Campo para Estado
    const estadoLabel = document.createElement('label');
    estadoLabel.textContent = 'Estado:';
    const estadoInput = document.createElement('input');
    estadoInput.type = 'text';
    estadoInput.name = 'estado';
    estadoInput.required = true;

    // Campo para CEP
    const cepLabel = document.createElement('label');
    cepLabel.textContent = 'CEP:';
    const cepInput = document.createElement('input');
    cepInput.type = 'text';
    cepInput.name = 'cep';
    cepInput.required = true;

    // Campo para Descrição
    const cargoLabel = document.createElement('label');
    cargoLabel.textContent = 'Cargo:';
    const cargoInput = document.createElement('input');
    cargoInput.name = 'cargo';
    cargoInput.required = true;

    // Campo para Competências
    const competenciasLabel = document.createElement('label');
    competenciasLabel.textContent = 'Competências:';
    const competenciasInput = document.createElement('input');
    competenciasInput.type = 'text';
    competenciasInput.name = 'competencias';
    competenciasInput.placeholder = 'Separe as competências por vírgula';
    competenciasInput.required = true;

    // Botão de Submissão
    const submitButton = document.createElement('button');
    submitButton.type = 'submit';
    submitButton.textContent = 'Cadastrar';

    // Adicionando os campos ao formulário
    form.appendChild(nomeLabel);
    form.appendChild(nomeInput);
    form.appendChild(emailLabel);
    form.appendChild(emailInput);
    form.appendChild(cnpjLabel);
    form.appendChild(cnpjInput);
    form.appendChild(paisLabel);
    form.appendChild(paisInput);
    form.appendChild(estadoLabel);
    form.appendChild(estadoInput);
    form.appendChild(cepLabel);
    form.appendChild(cepInput);
    form.appendChild(cargoLabel);
    form.appendChild(cargoInput);
    form.appendChild(competenciasLabel);
    form.appendChild(competenciasInput);
    form.appendChild(submitButton);

    return form;
}

document.addEventListener('DOMContentLoaded', () => {
    const app = document.querySelector('body');
    if (app) {
        app.appendChild(createCompanyForm());
    }
});
