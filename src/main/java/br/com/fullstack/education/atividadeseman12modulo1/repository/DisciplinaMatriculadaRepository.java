package br.com.fullstack.education.atividadeseman12modulo1.repository;

import br.com.fullstack.education.atividadeseman12modulo1.entity.DisciplinaMatriculadaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaMatriculadaRepository extends JpaRepository <DisciplinaMatriculadaEntity, Long> {
}
