package API.LibraryManagement.Acedig.Data.DTO;

import java.util.Date;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private boolean ativo;
    private Date dataCadastro;

    public UsuarioDTO(Long id, String nome, String email, boolean ativo, Date dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
