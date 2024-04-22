package br.com.fullstack.education.atividadeseman12modulo1.service;

import br.com.fullstack.education.atividadeseman12modulo1.entity.AlunoEntity;
import br.com.fullstack.education.atividadeseman12modulo1.entity.DisciplinaMatriculadaEntity;
import br.com.fullstack.education.atividadeseman12modulo1.exception.NotFoundException;
import br.com.fullstack.education.atividadeseman12modulo1.repository.AlunoRepository;
import br.com.fullstack.education.atividadeseman12modulo1.repository.DisciplinaMatriculadaRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Data
@Service
@AllArgsConstructor
public class DisciplinaMatriculadaService {

    private final DisciplinaMatriculadaRepository repository;

    public List<DisciplinaMatriculadaEntity> buscarTodasDisciplinasMatriculadas() {
        log.info("Buscando todas as disciplinas matriculadas");
        return repository.findAll();
    }






}
