/*
 * Project: visualiza-exames
 * @(#) ExameImage.java	 28/06/2016
 * @(#)br.com.pixeon.visualiza.domain
 *
 * Copyright 2016 . All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package br.com.pixeon.visualiza.domain;

import javax.persistence.*;
import javax.servlet.annotation.HttpConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by ricardo on 28/06/16.
 */
@Entity
@Table(name = "tb_exame_image")
public class ExameImage  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private Long imageId;

    @Column(length = 255, nullable = false)
    private String uri;

    @ElementCollection
    @MapKeyColumn(name = "chave", unique = true)
    @Column(name = "valor")
    @CollectionTable(name = "tb_exame_image_param", joinColumns=@JoinColumn(name="image_id"))
    private Map<String, String> parameters = new HashMap<>();

    @ManyToOne
    private Exame exame;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExameImage that = (ExameImage) o;
        return Objects.equals(exame, that.exame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exame);
    }

    @Override
    public String toString() {
        return "ExameImage{" +
                "imageId=" + imageId +
                ", uri='" + uri + '\'' +
                ", parameters=" + parameters +
                ", exame=" + exame +
                '}';
    }
}
