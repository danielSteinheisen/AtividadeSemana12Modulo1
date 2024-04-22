package br.com.fullstack.education.atividadeseman12modulo1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
}
