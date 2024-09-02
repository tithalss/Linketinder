import {
    Chart,
    BarController,
    BarElement,
    CategoryScale,
    LinearScale,
    Title,
    Tooltip,
    Legend,
} from 'chart.js';

Chart.register(
    BarController,
    BarElement,
    CategoryScale,
    LinearScale,
    Title,
    Tooltip,
    Legend
);



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

// Função que monta o card de candidato, elementos + botão de like
function createCandidateCard(candidato: Candidate): HTMLElement {
    const candidateCard = document.createElement("div");
    candidateCard.className = "candidate-card";

    const photo = document.createElement("img");
    photo.src = '/Linketinder/frontend-ts/public/assets/Anon.png';
    photo.alt = 'Foto Anom';
    photo.className = "candidate-photo";
    
    const infoContainer = document.createElement("div");
    infoContainer.className = "candidate-info";

    const jobTitle = document.createElement("h3");
    jobTitle.textContent = candidato.cargo;
    jobTitle.className = "job-title";

    const competenciesList = document.createElement("p");
    competenciesList.textContent = `Competências: ${candidato.competencias.join(', ')}`;
    competenciesList.className = "competencies";

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

    infoContainer.appendChild(jobTitle);
    infoContainer.appendChild(competenciesList);

    candidateCard.appendChild(photo);
    candidateCard.appendChild(infoContainer);
    candidateCard.appendChild(likeButton);

    return candidateCard;
}

// Função para carregar os candidatos
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
        candidatesContainer.innerHTML = "";

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

            userProfileDiv.innerHTML = `
                <p>${empresa.nome}</p>
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

// Função para contar competências dos candidatos
function countCompetencies(candidates: Candidate[]): { [key: string]: number } {
    const competenciesCount: { [key: string]: number } = {};

    candidates.forEach(candidate => {
        candidate.competencias.forEach(competencia => {
            if (competenciesCount[competencia]) {
                competenciesCount[competencia]++;
            } else {
                competenciesCount[competencia] = 1;
            }
        });
    });

    return competenciesCount;
}

// Função para criar o gráfico de barras
function createCompetenciesChart(candidates: Candidate[]): void {
    const competenciesCount = countCompetencies(candidates);
    const ctx = document.getElementById('competenciesChart') as HTMLCanvasElement;

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: Object.keys(competenciesCount),
            datasets: [{
                label: 'Gráfico de competências',
                data: Object.values(competenciesCount),
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function registerJob() {
    const jobTitleInput = document.getElementById("jobTitle") as HTMLInputElement;
    const jobDescriptionInput = document.getElementById("jobDescription") as HTMLTextAreaElement;

    const jobTitle = jobTitleInput.value.trim();
    const jobDescription = jobDescriptionInput.value.trim();

    if (jobTitle && jobDescription) {
        const job = { title: jobTitle, description: jobDescription };

        // Carrega as vagas
        let jobs = JSON.parse(localStorage.getItem("jobs") || "[]");
        jobs.push(job);

        // Salva a lista atualizada no localStorage
        localStorage.setItem("jobs", JSON.stringify(jobs));

        alert("Vaga registrada com sucesso.")

        jobTitleInput.value = "";
        jobDescriptionInput.value = "";

    } else {
        alert("Por favor, preencha todos os campos para cadastrar a vaga.");
    }
}

// Associa a função ao botão de cadastro
document.getElementById("registerJobButton")?.addEventListener("click", registerJob);

document.addEventListener('DOMContentLoaded', () => {
    loadCompanyProfile();

    const candidates = loadCandidates();

    createCompetenciesChart(candidates);
    renderCandidateCards(candidates);
});
