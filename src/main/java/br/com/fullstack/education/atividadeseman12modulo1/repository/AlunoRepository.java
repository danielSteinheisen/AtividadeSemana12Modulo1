package br.com.fullstack.education.atividadeseman12modulo1.repository;


import br.com.fullstack.education.atividadeseman12modulo1.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
}
