package br.com.fullstack.education.atividadeseman12modulo1.controller;

import br.com.fullstack.education.atividadeseman12modulo1.entity.ProfessorEntity;
import br.com.fullstack.education.atividadeseman12modulo1.service.ProfessorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService service;

    @PostMapping
    public ResponseEntity<ProfessorEntity> criarProfessor(@RequestBody ProfessorEntity professor) {
        log.info("Criando novo Professor");
        try {
            ProfessorEntity professorSalvo = service.salvarProfessor(professor);
            return new ResponseEntity<>(professorSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Erro ao criar novo Professor", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> buscarTodosProfessores() {
        try {
            log.info("Buscando todos os Professores");
            List<ProfessorEntity> professores = service.buscarTodosProfessores();
            if (professores.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(professores, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Erro ao buscar todos os Professores", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorEntity> buscarProfessorPorId(@PathVariable Long id) {
        log.info("Buscando o Professor pelo ID: {}", id);
        ProfessorEntity professor = service.buscarProfessorPorId(id);
        if (professor != null) {
            return new ResponseEntity<>(professor, HttpStatus.OK);
        } else {
            log.error("Professor com o ID {} não encontrado", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorEntity> atualizarProfessor(@PathVariable Long id, @RequestBody ProfessorEntity professor) {
        log.info("Atualizando o Professor pelo ID: {}", id);
        ProfessorEntity existeProfessor = service.buscarProfessorPorId(id);
        if (existeProfessor != null) {
            existeProfessor.setNome(professor.getNome());
            return new ResponseEntity<>(service.salvarProfessor(existeProfessor), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarProfessor(@PathVariable Long id) {
        log.info("Apagando o Professor pelo ID: {}", id);
        service.apagarProfessor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}