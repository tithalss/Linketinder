package org.example.Views

import org.example.Controllers.JobController
import org.example.Models.Job

class JobView {

    private final JobController jobController
    private final Scanner scanner

    JobView(JobController jobController) {
        this.jobController = jobController
        this.scanner = new Scanner(System.in)
    }

    void displayMenu() {
        Scanner scanner = new Scanner(System.in)

        while (true) {
            println "==== Menu de Candidatos ===="
            println "1. Criar nova Vaga"
            println "2. Atualizar vaga"
            println "3. Buscar vaga por ID"
            println "4. Deletar vaga"
            println "5. Voltar"
            print "Escolha uma opção: "
            def option = scanner.nextInt()
            scanner.nextLine() // Consumir a quebra de linha

            switch (option) {
                case 1:
                    createJob(scanner)
                    break
                case 2:
                    updateJob(scanner)
                    break
                case 3:
                    getJobById(scanner)
                    break
                case 4:
                    deleteJob(scanner)
                    break
                case 5:
                    println "Saindo..."
                    return
                default:
                    println "Opção inválida!"
            }
        }
    }

    void createJob(Scanner scanner) {
        println "Criar uma nova vaga:"

        print "Cargo: "
        String cargo = scanner.nextLine()

        print "Descrição: "
        String descricao = scanner.nextLine()

        print "Local: "
        String local = scanner.nextLine()

        print "ID da empresa: "
        int idEmpresa = Integer.parseInt(scanner.nextLine())

        Job job = new Job(1, cargo, descricao, local, idEmpresa)
        
        jobController.createJob(job)

        println "Vaga criada com sucesso!"
    }

    void getJobById(Scanner scanner) {
        print "Digite o ID da vaga que deseja buscar: "
        int id = Integer.parseInt(scanner.nextLine())

        Job job = jobController.getJobById(id)
        if (job) {
            println "Vaga encontrada:"
            println "Cargo: ${job.cargo}"
            println "Descrição: ${job.descricao}"
            println "Local: ${job.local}"
        } else {
            println "Vaga não encontrada."
        }
    }

    void updateJob(Scanner scanner) {
        print "Digite o ID da vaga que deseja atualizar: "
        int id = Integer.parseInt(scanner.nextLine())

        Job existingJob = jobController.getJobById(id)
        if (existingJob) {
            println "Atualizando vaga:"
            print "Novo Cargo (atual: ${existingJob.cargo}): "
            String cargo = scanner.nextLine()

            print "Nova Descrição (atual: ${existingJob.descricao}): "
            String descricao = scanner.nextLine()

            print "Novo Local (atual: ${existingJob.local}): "
            String local = scanner.nextLine()

            print "Novo ID da empresa (atual: ${existingJob.idEmpresa}): "
            int idEmpresa = Integer.parseInt(scanner.nextLine())

            Job updatedJob = new Job(id, cargo, descricao, local, idEmpresa)
            jobController.updateJob(updatedJob)
            println "Vaga atualizada com sucesso."
        } else {
            println "Vaga não encontrada."
        }
    }

    void deleteJob(Scanner scanner) {
        print "Digite o ID da vaga que deseja deletar: "
        int id = Integer.parseInt(scanner.nextLine())
        jobController.deleteJob(id)
        println "Vaga deletada com sucesso."
    }
}
