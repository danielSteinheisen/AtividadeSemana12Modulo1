package br.com.fullstack.education.atividadeseman12modulo1.service;

import br.com.fullstack.education.atividadeseman12modulo1.entity.MatriculaEntity;
import br.com.fullstack.education.atividadeseman12modulo1.entity.NotasEntity;
import br.com.fullstack.education.atividadeseman12modulo1.exception.NotFoundException;
import br.com.fullstack.education.atividadeseman12modulo1.repository.MatriculaRepository;
import br.com.fullstack.education.atividadeseman12modulo1.repository.NotasRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class MatriculaService {

    private final MatriculaRepository matricularepository;
    private final NotasRepository notasRepository;

    // Método pra calcular a média.
    public void calcularMedia(MatriculaEntity matricula) {
        log.info("Calculando a média da matricula");
        double somaNotas = 0;
        double somaCoeficientes = 0;
        for (NotasEntity nota : matricula.getNota()) {
            somaNotas += nota.getNota() * nota.getCoeficiente();
            somaCoeficientes += nota.getCoeficiente();
        }
        double media = somaNotas / somaCoeficientes;
        matricula.setMediaFinal(media);
        matricularepository.save(matricula);

    }

    public List<MatriculaEntity> buscarTodasMatriculas() {
        log.info("Buscando todas as matriculas");
        return matricularepository.findAll();
    }

    public MatriculaEntity salvarMatricula(MatriculaEntity matricula) {
        log.info("Salvando a matricula: {}", matricula);
        return matricularepository.save(matricula);
    }


    public MatriculaEntity buscarMatriculaPorId(Long id) {
        log.info("Buscando a matricula pelo ID: {}", id);
        return matricularepository.findById(id).orElseThrow(() ->
                new NotFoundException("Matricula não encontrada com o ID: " + id));
    }

    public List<MatriculaEntity> buscarMatriculasPorAluno(Long alunoId) {
        log.info("Buscando matriculas pelo ID do Aluno: {}", alunoId);
        return matricularepository.findByAlunoId(alunoId);
    }

    public List<MatriculaEntity> buscarMatriculasPorDisciplina(Long disciplinaId) {
        log.info("Buscando matriculas pelo ID da Disciplina: {}", disciplinaId);
        return matricularepository.findByDisciplinaId(disciplinaId);
    }

    public void apagarMatricula(Long id) {
        if (notasRepository.findByMatriculaId(id).isEmpty()) {
            log.info("Apagando a matricula pelo ID: {}", id);
            matricularepository.deleteById(id);
        }
        else {
            log.error("A matricula com o ID {} não pode ser excluída porque existem notas associadas.", id);
            throw new NotFoundException("A matricula com o ID " + id +
                    " não pode ser excluída porque existem notas associadas.");
        }

    }


}
