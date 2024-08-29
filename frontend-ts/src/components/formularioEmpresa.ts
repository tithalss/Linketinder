document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formulario-empresa") as HTMLFormElement;

    form.addEventListener("submit", (event) => {
        event.preventDefault();

        const nome = (document.getElementById("nome") as HTMLInputElement).value;
        const email = (document.getElementById("email") as HTMLInputElement).value;
        const cnpj = (document.getElementById("cnpj") as HTMLInputElement).value;
        const pais = (document.getElementById("pais") as HTMLInputElement).value;
        const estado = (document.getElementById("estado") as HTMLInputElement).value;
        const cep = (document.getElementById("cep") as HTMLInputElement).value;
        const descricao = (document.getElementById("descricao") as HTMLInputElement).value;

        const empresa = {
            id: Date.now(),
            nome,
            email,
            cnpj,
            pais,
            estado,
            cep,
            descricao,
        };

        // Recuperar a lista de empresas do localStorage
        let empresas = JSON.parse(localStorage.getItem('empresas') || '[]');
        
        // Adicionar nova empresa à lista
        empresas.push(empresa);

        // Salvar a lista atualizada no localStorage
        localStorage.setItem('empresas', JSON.stringify(empresas));
        
        // Salva a empresa atual no localStorage
        localStorage.setItem(`empresa-${empresa.id}`, JSON.stringify(empresa));
        
        // Opcional: armazena o ID da empresa atual para referência futura
        localStorage.setItem('empresaAtual', empresa.id.toString());

        // Opcional: redireciona para outra página ou exibe uma mensagem
        window.location.href = "perfilEmpresa.html"; // Redireciona para uma página de perfil do candidato
    });
});
