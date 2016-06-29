package br.com.pixeon.visualiza.controller;

import br.com.pixeon.visualiza.ExameApplication;
import br.com.pixeon.visualiza.domain.Clinica;
import br.com.pixeon.visualiza.domain.Exame;
import br.com.pixeon.visualiza.domain.ExameImage;
import br.com.pixeon.visualiza.repository.ExameImageRepository;
import br.com.pixeon.visualiza.repository.ExameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

import static org.hamcrest.Matchers.hasItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * ExameController REST controller para teste.
 *
 * @see ExameController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ExameApplication.class)
@WebAppConfiguration
@IntegrationTest
public class ExameControllerTest {


    @Inject
    private ExameRepository exameRepository;

    @Inject
    private ExameImageRepository exameImageRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restExameMockMvc;

    private Exame exame;

    private Clinica clinica;

    private ExameImage exameImage;

    @PostConstruct
    public void config() {
        MockitoAnnotations.initMocks(this);
        ExameController exameResource = new ExameController();
        ReflectionTestUtils.setField(exameResource, "exameRepository", exameRepository);
        ReflectionTestUtils.setField(exameResource, "exameImageRepository", exameImageRepository);
        this.restExameMockMvc = MockMvcBuilders.standaloneSetup(exameResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        //Precisa da clinica
        clinica = new Clinica();
        clinica.setRazaoSocial("Primeira empresa para ser testada");
        clinica.setNomeFantasia("Emrpesa que conhecemos");
        //As imagens são tratadas como metadados, apontando diretamente para API REST.Nao ficando em uma tabela configuracao separada
        exameImage = new ExameImage();
        exameImage.setUri("http://localhost:8080/api/exame/image");
        Map<String,String> parameters = new HashMap<>();
        //parametros necessario para montagem da query
        parameters.put("imageId","1");
        //ex resultado final :http://localhost:8080/api/exame/image?imageId=1
        exameImage.setParameters(parameters);
        //Exame
        exame = new Exame();
        exame.setClinica(clinica);
        Set<ExameImage> images = new HashSet<>();
        images.add(exameImage);
        exame.setImages(images);
    }

    @Test
    @Transactional
    public void listaAllExames() throws Exception {
        exameRepository.saveAndFlush(exame);

        restExameMockMvc.perform(get("/api/exames"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(exame.getId().intValue())));
    }

    @Test
    @Transactional
    public void getNonExistingExame() throws Exception {
        restExameMockMvc.perform(get("/api/exames/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void getExame() throws Exception {
        exameRepository.saveAndFlush(exame);

        restExameMockMvc.perform(get("/api/exames/{id}", exame.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(exame.getId().intValue()));
    }

    @Test
    @Transactional
    public void getExameImageURL() throws Exception {
        exameRepository.saveAndFlush(exame);

        restExameMockMvc.perform(get("/api/exames/image/{id}", exameImage.getImageId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("\"http://localhost:8080/api/exame/image?imageId=1\""));
    }


}
