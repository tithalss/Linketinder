document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formulario-candidato") as HTMLFormElement;

    form.addEventListener("submit", (event) => {
        event.preventDefault();

        const nome = (document.getElementById("nomeCompleto") as HTMLInputElement).value;
        const email = (document.getElementById("email") as HTMLInputElement).value;
        const cpf = (document.getElementById("cpf") as HTMLInputElement).value;
        const idade = (document.getElementById("idade") as HTMLInputElement).value;
        const estado = (document.getElementById("estado") as HTMLInputElement).value;
        const cep = (document.getElementById("cep") as HTMLInputElement).value;
        const cargo = (document.getElementById("cargo") as HTMLInputElement).value;
        const competencias = (document.getElementById("competencias") as HTMLInputElement).value;

        const candidato = {
            id: Date.now(), // Gera ID baseado no timestamp
            nome,
            email,
            cpf,
            idade,
            estado,
            cep,
            cargo,
            competencias: competencias.split(",").map(comp => comp.trim())
        };

        // Recuperar lista de candidatos do localStorage
        let candidatos = JSON.parse(localStorage.getItem('candidatos') || '[]');
        candidatos.push(candidato);

        // Salvar a lista atualizada no localStorage
        localStorage.setItem('candidatos', JSON.stringify(candidatos));
        
        // Salva o candidato atual no localStorage
        localStorage.setItem(`candidato-${candidato.id}`, JSON.stringify(candidato));
        
        // Armazena o ID do candidato atual
        localStorage.setItem('candidatoAtual', candidato.id.toString());
        window.location.href = "perfilCandidato.html";
    });
});
