package br.com.fullstack.education.atividadeseman12modulo1.repository;

import br.com.fullstack.education.atividadeseman12modulo1.entity.NotasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface NotasRepository extends JpaRepository<NotasEntity, Long> {
    Collection<Object> findByMatriculaId(Long id);

    List<NotasEntity> findByDisciplinaMatriculadaId(Long id);
}
