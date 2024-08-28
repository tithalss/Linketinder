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
});
