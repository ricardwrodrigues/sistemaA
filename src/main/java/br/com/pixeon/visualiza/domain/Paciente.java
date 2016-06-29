/*
 * Project: visualiza-exames
 * @(#) Paciente.java	 28/06/2016
 * @(#)br.com.pixeon.visualiza.domain
 *
 * Copyright 2016  . All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package br.com.pixeon.visualiza.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade Paciente.
 */
@Entity
@Table(name = "tb_paciente")
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size( max = 100)
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @NotNull
    @Size( max = 150)
    @Column(name = "sobrenome", length = 150, nullable = false)
    private String sobrenome;

    @NotNull
    @Email
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @NotNull
    @Size(min = 11, max = 11)
    @Column(name = "cpf")
    private String cpf;

    @OneToMany(mappedBy = "paciente")
    private Set<Exame> exames = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Exame> getExames() {
        return exames;
    }

    public void setExames(Set<Exame> exames) {
        this.exames = exames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Paciente paciente = (Paciente) o;
        if(paciente.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, paciente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nome='" + nome + "'" +
                ", sobrenome='" + sobrenome + "'" +
                ", email='" + email + "'" +
                ", cpf='" + cpf + "'" +
                '}';
    }
}