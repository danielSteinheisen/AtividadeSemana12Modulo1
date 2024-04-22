package br.com.fullstack.education.atividadeseman12modulo1.service;

import br.com.fullstack.education.atividadeseman12modulo1.entity.DisciplinaMatriculadaEntity;
import br.com.fullstack.education.atividadeseman12modulo1.exception.NotFoundException;
import br.com.fullstack.education.atividadeseman12modulo1.repository.DisciplinaMatriculadaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DisciplinaService {

    private final DisciplinaMatriculadaRepository repository;

    public List<DisciplinaMatriculadaEntity> buscarTodasDisciplinas() {
        log.info("Buscando todas as disciplinas");
        return repository.findAll();
    }

    public DisciplinaMatriculadaEntity buscarDisciplinaPorId(Long id) {
        log.info("Buscando a disciplina pelo ID: {}", id);
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Disciplina não encontrada com o ID: " + id));
    }

    public DisciplinaMatriculadaEntity salvarDisciplina(DisciplinaMatriculadaEntity disciplina) {
        log.info("Salvando disciplina");
        return repository.save(disciplina);
    }

    public DisciplinaMatriculadaEntity atualizarDisciplina(Long id, DisciplinaMatriculadaEntity disciplina) {
        log.info("Atualizando a disciplina pelo ID: {}", id);
        DisciplinaMatriculadaEntity existeDisciplina = repository.findById(id).orElse(null);
        if (existeDisciplina != null) {
            existeDisciplina.setNome(disciplina.getNome());

            return repository.save(existeDisciplina);
        }
        return null;
    }

    public void apagarDisciplina(Long id) {
        log.info("Apagando a disciplina pelo ID: {}", id);
        repository.deleteById(id);
    }



}
