package br.com.fullstack.education.atividadeseman12modulo1.service;

import br.com.fullstack.education.atividadeseman12modulo1.entity.DisciplinaMatriculadaEntity;
import br.com.fullstack.education.atividadeseman12modulo1.entity.MatriculaEntity;
import br.com.fullstack.education.atividadeseman12modulo1.entity.NotasEntity;
import br.com.fullstack.education.atividadeseman12modulo1.exception.NotFoundException;
import br.com.fullstack.education.atividadeseman12modulo1.repository.DisciplinaMatriculadaRepository;
import br.com.fullstack.education.atividadeseman12modulo1.repository.MatriculaRepository;
import br.com.fullstack.education.atividadeseman12modulo1.repository.NotasRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NotasService {

    private final NotasRepository notasRepository;
    private final DisciplinaMatriculadaRepository disciplinaMatriculadaRepository;

    public NotasEntity adicionarNota(Long matriculaId, Double nota, Double coeficiente) {
        DisciplinaMatriculadaEntity matricula = disciplinaMatriculadaRepository.findById(matriculaId).orElseThrow(() ->
                new NotFoundException("Matricula não encontrada"));

        NotasEntity novaNota = new NotasEntity();
        novaNota.setDisciplinaMatriculada(matricula);
        novaNota.setNota(nota);
        novaNota.setCoeficiente(coeficiente);
        notasRepository.save(novaNota);

        atualizarMediaFinal(matricula);

        return novaNota;
    }

    private void atualizarMediaFinal(DisciplinaMatriculadaEntity matricula) {
        List<NotasEntity> notas = notasRepository.findByDisciplinaMatriculadaId(matricula.getId());
        double media = notas.stream()
                .mapToDouble(n -> n.getNota() * n.getCoeficiente())
                .sum() / notas.stream().mapToDouble(n -> n.getCoeficiente()).sum();
        matricula.setMediaFinal(media);
        disciplinaMatriculadaRepository.save(matricula);
    }

    public List<NotasEntity> buscarNotasPorMatricula(Long matriculaId) {
        return notasRepository.findByDisciplinaMatriculadaId(matriculaId);

    }

    public void removerNota(Long notaId) {
        NotasEntity nota = notasRepository.findById(notaId).orElseThrow(() ->
                new NotFoundException("Nota não encontrada"));
        DisciplinaMatriculadaEntity matricula =nota.getDisciplinaMatriculada();
        notasRepository.delete(nota);
        atualizarMediaFinal(matricula);

    }

}
