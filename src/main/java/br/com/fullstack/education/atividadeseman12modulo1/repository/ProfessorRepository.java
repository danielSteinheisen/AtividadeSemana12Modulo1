package br.com.fullstack.education.atividadeseman12modulo1.repository;

import br.com.fullstack.education.atividadeseman12modulo1.entity.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}
