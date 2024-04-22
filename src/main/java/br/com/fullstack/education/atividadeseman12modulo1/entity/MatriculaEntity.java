package br.com.fullstack.education.atividadeseman12modulo1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class MatriculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long alunoId;
    private Long disciplinaId;

    @Temporal(TemporalType.DATE)
    private Date dataMatricula;
    private Double mediaFinal;

    @ManyToOne
    @JoinColumn(name = "alunoId", insertable=false, updatable=false)
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "disciplinaId", insertable=false, updatable=false)
    private DisciplinaMatriculadaEntity disciplina;

    public NotasEntity[] getNota() {
        return new NotasEntity[0];
    }
}
