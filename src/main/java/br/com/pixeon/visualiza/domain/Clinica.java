/*
 * Project: visualiza-exames
 * @(#) Clinica.java	 28/06/2016
 * @(#)br.com.pixeon.visualiza.domain
 *
 * Copyright 2016 . All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package br.com.pixeon.visualiza.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade clinica.
 */
@Entity
@Table(name = "tb_clinica")
public class Clinica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 150)
    @Column(name = "razao_social", length = 150, nullable = false)
    private String razaoSocial;

    @NotNull
    @Size( max = 150)
    @Column(name = "nome_fantasia", length = 150, nullable = false)
    private String nomeFantasia;

    @OneToMany(mappedBy = "clinica")
    private Set<Exame> exames = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
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
        Clinica clinica = (Clinica) o;
        if(clinica.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, clinica.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Clinica{" +
                "id=" + id +
                ", razaoSocial='" + razaoSocial + "'" +
                ", nomeFantasia='" + nomeFantasia + "'" +
                '}';
    }
}
