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

        if (!/^[a-zA-ZÀ-ÖØ-öø-ÿ0-9\s]+$/.test(nome)) {
            alert("ERRO: Nome inválido.");
            return;
        }        

        if (!/^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email)) {
            alert("ERRO: Email inválido.");
            return;
        }

        if (!/^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/.test(cnpj)) {
            alert("ERRO: CNPJ inválido.");
            return;
        }

        if (!/^[A-Za-zÀ-ÖØ-öø-ÿ\s]+$/.test(pais)) {
            alert("ERRO: País inválido.");
            return;
        }
        
        if (!/^[A-Za-zÀ-ÖØ-öø-ÿ\s]+$/.test(estado)) {
            alert("ERRO: Estado inválido.");
            return;
        }        

        if (!/^\d{5}-\d{3}$/.test(cep)) {
            alert("ERRO: CEP inválido.");
            return;
        }

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
