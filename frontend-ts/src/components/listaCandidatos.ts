import { Chart, ChartConfiguration, ChartData, ChartOptions, registerables } from 'chart.js';

// Registrando os componentes necessários para o Chart.js funcionar
Chart.register(...registerables);

export function createCompetenciesChart(): void {
    const ctx = document.getElementById('competenciesChart') as HTMLCanvasElement;

    if (ctx) {
        // Dados para o gráfico
        const data: ChartData<'bar'> = {
            labels: ['Python', 'Java', 'JavaScript', 'C#', 'Ruby'],
            datasets: [{
                label: 'Número de Candidatos',
                data: [12, 19, 3, 5, 2], // Substitua pelos dados reais
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        };

        // Configuração do gráfico
        const config: ChartConfiguration<'bar'> = {
            type: 'bar',
            data: data,
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            } as ChartOptions<'bar'> // Adiciona o tipo ChartOptions para garantir a compatibilidade
        };

        // Renderiza o gráfico
        new Chart(ctx, config);
    } else {
        console.error("Elemento 'competenciesChart' não encontrado.");
    }
}

function createJobListing(jobs: Array<{ titulo: string, descricao: string }>): HTMLElement {
    const header = document.createElement("header");
    header.classList.add("br");

    const container = document.createElement('div');
    container.classList.add('job-listing-container');

    jobs.forEach(job => {
        const jobCard = document.createElement('div');
        jobCard.classList.add('job-card');

        const jobTitle = document.createElement('h3');
        jobTitle.textContent = job.titulo;
        jobCard.appendChild(jobTitle);

        const jobDescription = document.createElement('p');
        jobDescription.textContent = job.descricao;
        jobCard.appendChild(jobDescription);

        const likeButton = document.createElement('button');
        likeButton.innerHTML = '♡'; // Coração vazio
        likeButton.classList.add('like-button', 'empty');
        
        likeButton.addEventListener('click', () => {
            if (likeButton.classList.contains('empty')) {
                likeButton.innerHTML = '♥'; // Coração preenchido
                likeButton.classList.remove('empty');
                likeButton.classList.add('filled');
            } else {
                likeButton.innerHTML = '♡'; // Volta para o coração vazio
                likeButton.classList.remove('filled');
                likeButton.classList.add('empty');
            }
        });

        jobCard.appendChild(likeButton);
        container.appendChild(jobCard);
    });

    return container;
}

document.addEventListener('DOMContentLoaded', () => {
    createCompetenciesChart();
    const jobs = [
        { titulo: 'Desenvolvedor Full Stack', descricao: 'Vaga para desenvolvedor com experiência em JavaScript, Node.js, e React.' },
        { titulo: 'Analista de Dados', descricao: 'Vaga para analista com habilidades em SQL, Python e Machine Learning.' },
        { titulo: 'Desenvolvedor Full Stack', descricao: 'Vaga para desenvolvedor com experiência em JavaScript, Node.js, e React.' },
        { titulo: 'Analista de Dados', descricao: 'Vaga para analista com habilidades em SQL, Python e Machine Learning.' },
        { titulo: 'Desenvolvedor Full Stack', descricao: 'Vaga para desenvolvedor com experiência em JavaScript, Node.js, e React.' },
        { titulo: 'Analista de Dados', descricao: 'Vaga para analista com habilidades em SQL, Python e Machine Learning.' },
        { titulo: 'Desenvolvedor Full Stack', descricao: 'Vaga para desenvolvedor com experiência em JavaScript, Node.js, e React.' },
        { titulo: 'Analista de Dados', descricao: 'Vaga para analista com habilidades em SQL, Python e Machine Learning.' },
        { titulo: 'Desenvolvedor Full Stack', descricao: 'Vaga para desenvolvedor com experiência em JavaScript, Node.js, e React.' },
        { titulo: 'Analista de Dados', descricao: 'Vaga para analista com habilidades em SQL, Python e Machine Learning.' },
        { titulo: 'Desenvolvedor Full Stack', descricao: 'Vaga para desenvolvedor com experiência em JavaScript, Node.js, e React.' },
        // Adicione mais vagas conforme necessário
    ];

    const app = document.querySelector('body');
    if (app) {
        app.appendChild(createJobListing(jobs));
    }
    //createCompetenciesChart();
});