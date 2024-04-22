package br.com.fullstack.education.atividadeseman12modulo1.controller;

import br.com.fullstack.education.atividadeseman12modulo1.entity.AlunoEntity;
import br.com.fullstack.education.atividadeseman12modulo1.service.AlunoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService service;

    @PostMapping
    public ResponseEntity<AlunoEntity> criarAluno(@RequestBody AlunoEntity aluno) {
        log.info("Criando novo aluno");
        try {
            AlunoEntity alunoSalvo = service.salvarAluno(aluno);
            return new ResponseEntity<>(alunoSalvo, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error("Erro ao criar novo aluno", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoEntity> buscarAlunoPorId(@PathVariable Long id) {
        log.info("Buscando aluno por ID");
        AlunoEntity aluno = service.buscarAlunoPorId(id);
        if(aluno != null) {
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        }
        else {
            log.error("Aluno com o ID {} não encontrado", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> buscarTodosAlunos() {
        try {
            log.info("Buscando todos os alunos");
            List<AlunoEntity> alunos = service.buscarTodosAlunos();
            if (alunos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(alunos,HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Erro ao buscar todos os alunos", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoEntity> atualizarAluno(@PathVariable Long id, @RequestBody AlunoEntity Aluno) {
        AlunoEntity atualizado = service.atualizarAluno(id, Aluno);
        if(atualizado != null) {
            log.error("Não foi possível atualizar. Aluno com o ID {} não encontrado", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(atualizado,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarAluno(@PathVariable Long id) {
        log.info("Apagando o Aluno pelo ID: {}", id);
        service.apagarAluno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
