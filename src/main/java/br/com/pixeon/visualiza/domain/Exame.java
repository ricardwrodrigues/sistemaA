/*
 * Project: visualiza-exames
 * @(#) Exame.java	 28/06/2016
 * @(#)br.com.pixeon.visualiza.domain
 *
 * Copyright 2016 . All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package br.com.pixeon.visualiza.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidade Exame.
 */
@Entity
@Table(name = "tb_exame")
public class Exame implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Clinica clinica;

    @ManyToOne
    private Paciente paciente;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "exame")
    private Set<ExameImage> images = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Set<ExameImage> getImages() {
        return images;
    }

    public void setImages(Set<ExameImage> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Exame exame = (Exame) o;
        if (exame.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, exame.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Exame{" +
                "id=" + id +
                '}';
    }
}