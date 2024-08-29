interface Candidate {
    id: number;
    nome: string;
    email: string;
    cpf: string;
    idade: string;
    estado: string;
    cep: string;
    cargo: string;
    competencias: string[];
}

function createCandidateCard(candidato: Candidate): HTMLElement {
    const candidateCard = document.createElement("div");
    candidateCard.className = "candidate-card";

    // Adiciona a foto genérica
    const photo = document.createElement("img");
    photo.src = '/Linketinder/frontend-ts/public/assets/Anon.png'; // Substitua pelo caminho da foto genérica
    photo.alt = 'Foto Genérica';
    photo.className = "candidate-photo";
    
    // Adiciona o container das informações
    const infoContainer = document.createElement("div");
    infoContainer.className = "candidate-info";

    const jobTitle = document.createElement("h3");
    jobTitle.textContent = candidato.cargo;
    jobTitle.className = "job-title";

    const competenciesList = document.createElement("p");
    competenciesList.textContent = `Competências: ${candidato.competencias.join(', ')}`;
    competenciesList.className = "competencies";

    // Botão de like
    const likeButton = document.createElement("button");
    likeButton.className = "like-button empty";
    likeButton.innerHTML = "♡";

    likeButton.addEventListener("click", () => {
        if (likeButton.classList.contains("empty")) {
            likeButton.innerHTML = "♥";
            likeButton.classList.remove("empty");
            likeButton.classList.add("filled");
        } else {
            likeButton.innerHTML = "♡";
            likeButton.classList.remove("filled");
            likeButton.classList.add("empty");
        }
    });

    // Monta o card
    infoContainer.appendChild(jobTitle);
    infoContainer.appendChild(competenciesList);

    candidateCard.appendChild(photo);
    candidateCard.appendChild(infoContainer);
    candidateCard.appendChild(likeButton);

    return candidateCard;
}

// Função para carregar os candidatos do localStorage
function loadCandidates(): Candidate[] {
    const candidates: Candidate[] = [];
    Object.keys(localStorage).forEach(key => {
        if (key.startsWith('candidato-')) {
            const candidateData = localStorage.getItem(key);
            if (candidateData) {
                candidates.push(JSON.parse(candidateData));
            }
        }
    });
    return candidates;
}

// Função para renderizar os cards de candidatos na página
function renderCandidateCards(candidates: Candidate[]): void {
    const candidatesContainer = document.querySelector(".candidates-container");

    if (candidatesContainer) {
        candidatesContainer.innerHTML = ""; // Limpa os cards existentes

        candidates.forEach(candidato => {
            const candidateCard = createCandidateCard(candidato);
            candidatesContainer.appendChild(candidateCard);
        });
    }
}

function loadCompanyProfile() {
    const userProfileDiv = document.getElementById('userProfile');
    
    if (!userProfileDiv) return;

    // Recuperar o ID do candidato atual
    const empresaAtualId = localStorage.getItem('empresaAtual');
    
    if (empresaAtualId) {
        // Recuperar os dados do candidato atual do localStorage
        const empresaData = localStorage.getItem(`empresa-${empresaAtualId}`);
        
        if (empresaData) {
            const empresa = JSON.parse(empresaData);

            // Preencher o conteúdo da aba de perfil
            userProfileDiv.innerHTML = `
                <p><strong>Nome Completo:</strong> ${empresa.nome}</p>
                <p><strong>Email:</strong> ${empresa.email}</p>
                <p><strong>CPF:</strong> ${empresa.cnpj}</p>
                <p><strong>País:</strong> ${empresa.pais}</p>
                <p><strong>Estado:</strong> ${empresa.estado}</p>
                <p><strong>CEP:</strong> ${empresa.cep}</p>
                <p><strong>Descrição:</strong> ${empresa.descricao}</p>
            `;
        } else {
            userProfileDiv.innerHTML = '<p>Dados da empresa não encontrados.</p>';
        }
    } else {
        userProfileDiv.innerHTML = '<p>ID da empresa atual não encontrado.</p>';
    }
}

// Executa quando o DOM estiver carregado
document.addEventListener('DOMContentLoaded', () => {
    loadCompanyProfile();

    const candidates = loadCandidates();
    renderCandidateCards(candidates);
});
