package br.com.compesa.conecta.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

import br.com.compesa.conecta.api.constants.TipoPublicacao;

@Entity
@Table(name = "TB_PUBLICACAO")
@Data
@EqualsAndHashCode(of = "id")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publicacao")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    @Lob
    private String legenda;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPublicacao tipo;

    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Midia> midias;

    @OneToOne(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Enquete enquete;
    
    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;
    
    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curtida> curtidas;

    @Column(updatable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

}