package br.com.pixeon.visualiza.repository;

import br.com.pixeon.visualiza.domain.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ricardo on 28/06/16.
 */
public interface ExameRepository extends JpaRepository<Exame, Long> {}
