package br.com.pixeon.visualiza.repository;

import br.com.pixeon.visualiza.domain.Exame;
import br.com.pixeon.visualiza.domain.ExameImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ricardo on 28/06/16.
 */
public interface ExameImageRepository extends JpaRepository<ExameImage, Long> {}
