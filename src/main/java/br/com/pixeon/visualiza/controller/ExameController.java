/*
 * Project: visualiza-exames
 * @(#) ExameController.java	 28/06/2016
 * @(#)br.com.pixeon.visualiza.controller
 *
 * Copyright 2016 . All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package br.com.pixeon.visualiza.controller;

import br.com.pixeon.visualiza.controller.util.ExameImageUtil;
import br.com.pixeon.visualiza.domain.Exame;
import br.com.pixeon.visualiza.domain.ExameImage;
import br.com.pixeon.visualiza.repository.ExameImageRepository;
import br.com.pixeon.visualiza.repository.ExameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Created by ricardo on 28/06/16.
 */
@Controller
@RequestMapping("/api")
public class ExameController {

    private final Logger log = LoggerFactory.getLogger(ExameController.class);

    @Inject
    private ExameRepository exameRepository;

    @Inject
    private ExameImageRepository exameImageRepository;


    /**
     * GET  /exames : lista todos as exames.
     *
     * @return um ResponseEntity com status 200 (OK) e lista exames dentro body
     */
    @RequestMapping(value = "/exames",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Exame> getAllExames() {
        log.debug("REST lista todos os Exames");
        List<Exame> exames = exameRepository.findAll();
        return exames;
    }


    /**
     * GET  /exames/:id : mostra exame "id".
     *
     * @param id do exame a ser mostrado
     * @return the ResponseEntity com status 200 (OK), ou com status 404 (Not Found)
     */
    @RequestMapping(value = "/exames/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Exame> getExame(@PathVariable Long id) {
        log.debug("REST retorna Exame : {}", id);
        Exame exame = exameRepository.findOne(id);
        return Optional.ofNullable(exame)
                .map(result -> new ResponseEntity<>(
                        result,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET  /exames/image:id : retorna url image.
     *
     * @param id da image a ser mostrado
     * @return the ResponseEntity com status 200 (OK), ou com status 404 (Not Found)
     */
    @RequestMapping(value = "/exames/image/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getExameImage(@PathVariable Long id) {
        log.debug("REST retorna image do exame : {}", id);
        ExameImage exameImage = exameImageRepository.findOne(id);
        if (exameImage == null) new ResponseEntity<>(HttpStatus.NOT_FOUND);
        String uri = ExameImageUtil.generateUri(exameImage.getUri(),exameImage.getParameters());
        return Optional.ofNullable(exameImage)
                .map(result -> new ResponseEntity<>(
                        uri,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
