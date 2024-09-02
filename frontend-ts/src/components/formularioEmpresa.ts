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

        // Recuperar lista de empresas do localStorage
        let empresas = JSON.parse(localStorage.getItem('empresas') || '[]');
        empresas.push(empresa);

        // Salvar a lista atualizada no localStorage
        localStorage.setItem('empresas', JSON.stringify(empresas));
        
        // Salva a empresa atual no localStorage
        localStorage.setItem(`empresa-${empresa.id}`, JSON.stringify(empresa));
        
        // Armazena o ID da empresa atual
        localStorage.setItem('empresaAtual', empresa.id.toString());
        window.location.href = "perfilEmpresa.html";
    });
});
