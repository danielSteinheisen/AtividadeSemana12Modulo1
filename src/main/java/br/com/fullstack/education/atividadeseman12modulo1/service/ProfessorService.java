package br.com.fullstack.education.atividadeseman12modulo1.service;

import br.com.fullstack.education.atividadeseman12modulo1.entity.ProfessorEntity;
import br.com.fullstack.education.atividadeseman12modulo1.exception.NotFoundException;
import br.com.fullstack.education.atividadeseman12modulo1.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public List<ProfessorEntity> buscarTodosProfessores() {
        log.info("Buscando todos os Professores");
        return professorRepository.findAll();
    }

    public ProfessorEntity buscarProfessorPorId(Long id) {
        log.info("Buscando o Professor pelo ID: {}", id);
        return professorRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Professor não encontrado com o ID: " + id));
    }

    public ProfessorEntity salvarProfessor(ProfessorEntity professor) {
        log.info("Salvando professor");
        return professorRepository.save(professor);
    }

    public ProfessorEntity atualizarProfessor(Long id, ProfessorEntity professor) {
        log.info("Atualizando o Professor pelo ID: {}", id);
        ProfessorEntity existeProfessor = professorRepository.findById(id).orElse(null);
        if (existeProfessor != null) {
            existeProfessor.setNome(professor.getNome());

            return professorRepository.save(existeProfessor);
        }
        return null;
    }

    public void apagarProfessor(Long id) {
        log.info("Apagando o Professor pelo ID: {}", id);
        professorRepository.deleteById(id);
    }


}
