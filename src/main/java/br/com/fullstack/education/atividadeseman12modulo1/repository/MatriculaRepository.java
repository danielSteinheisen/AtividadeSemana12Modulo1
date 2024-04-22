package br.com.fullstack.education.atividadeseman12modulo1.repository;

import br.com.fullstack.education.atividadeseman12modulo1.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Long> {
    List<MatriculaEntity> findByAlunoId(Long alunoId);

    List<MatriculaEntity> findByDisciplinaId(Long disciplinaId);
}
