package br.com.compesa.conecta.api.repository;

import br.com.compesa.conecta.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    UserDetails findByEmailCorporativo(String emailCorporativo);
    
}