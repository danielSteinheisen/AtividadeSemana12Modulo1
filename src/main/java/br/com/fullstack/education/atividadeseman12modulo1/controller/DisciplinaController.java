package br.com.fullstack.education.atividadeseman12modulo1.controller;

import br.com.fullstack.education.atividadeseman12modulo1.entity.DisciplinaMatriculadaEntity;
import br.com.fullstack.education.atividadeseman12modulo1.service.DisciplinaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<DisciplinaMatriculadaEntity> criarDisciplina(@RequestBody DisciplinaMatriculadaEntity disciplina) {
        log.info("Criando nova disciplina");
        try {
            DisciplinaMatriculadaEntity disciplinaSalva = disciplinaService.salvarDisciplina(disciplina);
            return new ResponseEntity<>(disciplinaSalva, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error("Erro ao criar nova disciplina", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaMatriculadaEntity>> buscarTodasDisciplinas() {
        try {
            log.info("Buscando todas as disciplinas");
            List<DisciplinaMatriculadaEntity> disciplinas = disciplinaService.buscarTodasDisciplinas();
            if (disciplinas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(disciplinas,HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Erro ao buscar todas as disciplinas", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaMatriculadaEntity> buscarDisciplinaPorId(@PathVariable Long id) {
        log.info("Buscando disciplina por ID");
        DisciplinaMatriculadaEntity disciplina = disciplinaService.buscarDisciplinaPorId(id);
        if(disciplina != null) {
            return new ResponseEntity<>(disciplina, HttpStatus.OK);
        }
        else {
            log.error("Disciplina com o ID {} não encontrada", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaMatriculadaEntity> atualizarDisciplina(@PathVariable Long id, @RequestBody DisciplinaMatriculadaEntity disciplina) {
        DisciplinaMatriculadaEntity atualizada = disciplinaService.atualizarDisciplina(id, disciplina);
        if(atualizada != null) {
            log.error("Não foi possível atualizar. Disciplina com o ID {} não encontrada", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(atualizada,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarDisciplina(@PathVariable Long id) {
        log.info("Apagando a disciplina pelo ID: {}", id);
        disciplinaService.apagarDisciplina(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
