interface Job {
    title: string;
    description: string;
}

// Função para carregar os jobs do localStorage
function loadJobs(): Job[] {
    const jobs = localStorage.getItem("jobs");
    return jobs ? JSON.parse(jobs) : [];
}

// Função para criar os elementos de job card
function createJobCard(job: Job): HTMLElement {
    const jobCard = document.createElement("div");
    jobCard.className = "job-card";

    const jobTitle = document.createElement("h3");
    jobTitle.textContent = job.title;

    const jobDescription = document.createElement("p");
    jobDescription.textContent = job.description;

    const likeButton = document.createElement("button");
    likeButton.className = "like-button empty";
    likeButton.innerHTML = "♡";

    // Adiciona o comportamento de "like" ao botão
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

    jobCard.appendChild(jobTitle);
    jobCard.appendChild(jobDescription);
    jobCard.appendChild(likeButton);

    return jobCard;
}

// Função que renderiza os job cards na página
function renderJobCards(): void {
    const jobListingContainer = document.querySelector(".job-listing-container");

    if (jobListingContainer) {
        jobListingContainer.innerHTML = "";

        const jobs = loadJobs();

        jobs.forEach(job => {
            const jobCard = createJobCard(job);
            jobListingContainer.appendChild(jobCard);
        });
    }
}

// Função que carrega o perfil do usuário
function loadCandidateProfile() {
    const userProfileDiv = document.getElementById('userProfile');
    
    if (!userProfileDiv) return;

    // Recuperar o ID do candidato atual
    const candidatoAtualId = localStorage.getItem('candidatoAtual');
    
    if (candidatoAtualId) {
        const candidatoData = localStorage.getItem(`candidato-${candidatoAtualId}`);
        
        if (candidatoData) {
            const candidato = JSON.parse(candidatoData);

            userProfileDiv.innerHTML = `
                <p>${candidato.nome}</p>
                <p><strong>Email:</strong> ${candidato.email}</p>
                <p><strong>CPF:</strong> ${candidato.cpf}</p>
                <p><strong>Idade:</strong> ${candidato.idade}</p>
                <p><strong>Estado:</strong> ${candidato.estado}</p>
                <p><strong>CEP:</strong> ${candidato.cep}</p>
                <p><strong>Cargo:</strong> ${candidato.cargo}</p>
                <p><strong>Competências:</strong> ${candidato.competencias.join(', ')}</p>
            `;
        } else {
            userProfileDiv.innerHTML = '<p>Dados do candidato não encontrados.</p>';
        }
    } else {
        userProfileDiv.innerHTML = '<p>ID do candidato atual não encontrado.</p>';
    }
}

document.addEventListener("DOMContentLoaded", () => {
    
    loadCandidateProfile();
    
    renderJobCards();
});
