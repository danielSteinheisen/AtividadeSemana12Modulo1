package br.com.fullstack.education.atividadeseman12modulo1.controller;

import br.com.fullstack.education.atividadeseman12modulo1.dto.MediaGeralDTO;
import br.com.fullstack.education.atividadeseman12modulo1.entity.MatriculaEntity;
import br.com.fullstack.education.atividadeseman12modulo1.service.MatriculaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    @PostMapping("/{id}/calcularMedia")
    public MatriculaEntity calcularMedia(@PathVariable Long id) {
        MatriculaEntity matricula = matriculaService.buscarMatriculaPorId(id);
        matriculaService.calcularMedia(matricula);
        return matricula;
    }

    @GetMapping("/mediaGeral/aluno/{alunoId}")
    public ResponseEntity<MediaGeralDTO> calcularMediaGeralPorAluno(@PathVariable Long alunoId) {
        try {
            double mediaGeral = matriculaService.calcularMedia(alunoId);
            MediaGeralDTO mediaGeralDTO = new MediaGeralDTO(mediaGeral);
            return ResponseEntity.ok(mediaGeralDTO);
        }
        catch (IllegalArgumentException e) {
            log.error("Erro ao calcular a média geral do aluno: {}", alunoId, e);
            return ResponseEntity.badRequest().build();
        }
        catch (Exception e) {
            log.error("Erro ao calcular média geral do aluno: {}",alunoId, e);
            return ResponseEntity.internalServerError().build();
        }

    }
}
