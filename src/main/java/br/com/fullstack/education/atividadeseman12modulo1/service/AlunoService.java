package br.com.fullstack.education.atividadeseman12modulo1.service;

import br.com.fullstack.education.atividadeseman12modulo1.entity.AlunoEntity;
import br.com.fullstack.education.atividadeseman12modulo1.exception.NotFoundException;
import br.com.fullstack.education.atividadeseman12modulo1.repository.AlunoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;

    public List<AlunoEntity> buscarTodosAlunos() {
        log.info("Buscando todos os Alunos");
        return repository.findAll();
    }

    public AlunoEntity salvarAluno(AlunoEntity entity) {
        log.info("Salvando o Aluno: {}", entity);
        return repository.save(entity);
    }

    public AlunoEntity buscarAlunoPorId(Long id) {
        log.info("Buscando o Aluno pelo ID: {}", id);
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Aluno não encontrado com o ID: " + id));
    }

    public AlunoEntity atualizarAluno(Long id, AlunoEntity aluno) {
        log.info("Atualizando o Aluno pelo ID: {}", id);
        AlunoEntity existeAluno = repository.findById(id).orElse(null);
        if (existeAluno != null) {
            existeAluno.setNome(aluno.getNome());
            existeAluno.setDataNascimento(aluno.getDataNascimento());
            return repository.save(existeAluno);
        }
        return null;
    }

    public void apagarAluno(Long id) {
        log.info("Apagando o Aluno pelo ID: {}", id);
        repository.deleteById(id);
    }
}
