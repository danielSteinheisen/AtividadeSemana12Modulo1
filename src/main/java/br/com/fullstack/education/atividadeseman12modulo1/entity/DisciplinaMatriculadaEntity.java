package br.com.fullstack.education.atividadeseman12modulo1.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class DisciplinaMatriculadaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long professorId;

    @ManyToOne
    @JoinColumn(name = "professorId", insertable = false, updatable = false)
    private ProfessorEntity professor;


    public void setMediaFinal(double media) {
    }
}
