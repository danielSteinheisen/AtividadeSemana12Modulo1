package br.com.fullstack.education.atividadeseman12modulo1.controller;

import br.com.fullstack.education.atividadeseman12modulo1.entity.NotasEntity;
import br.com.fullstack.education.atividadeseman12modulo1.service.NotasService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/notas")
public class NotasController {

    private final NotasService notasService;

    @PostMapping
    public ResponseEntity<NotasEntity> adicionarNota(@RequestBody NotasEntity notas) {
        log.info("Adicionando nova nota");
        NotasEntity novaNota = notasService.adicionarNota(notas.getDisciplinaMatriculadaId(),
                notas.getNota(),notas.getCoeficiente());
        return ResponseEntity.ok(novaNota);
    }

    @GetMapping("/matricula/{matriculaId}")
    public ResponseEntity<List<NotasEntity>> buscarNotasPorMatricula(@PathVariable Long matriculaId) {
        log.info("Buscando as notas pelo ID da matricula: {}", matriculaId);
        List<NotasEntity> notas =notasService.buscarNotasPorMatricula(matriculaId);
        return ResponseEntity.ok(notas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerNota(@PathVariable Long id) {
        log.info("Removendo a nota pelo ID: {}", id);
        notasService.removerNota(id);
        return ResponseEntity.noContent().build();
    }
}
