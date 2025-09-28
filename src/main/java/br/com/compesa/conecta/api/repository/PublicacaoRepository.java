package br.com.compesa.conecta.api.repository;

import br.com.compesa.conecta.api.model.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {
    
}