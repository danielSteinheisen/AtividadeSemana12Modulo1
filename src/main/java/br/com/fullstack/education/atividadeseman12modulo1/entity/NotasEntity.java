package br.com.fullstack.education.atividadeseman12modulo1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class NotasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long disciplinaMatriculadaId;
    private Long professorId;
    private Double nota;
    private Double coeficiente;

    @ManyToOne
    @JoinColumn(name = "disciplinaMatriculadaId", insertable=false, updatable=false)
    private DisciplinaMatriculadaEntity disciplinaMatriculada;
}
