package API.LibraryManagement.Acedig.Data.DTO;

import java.util.Date;

public class NotificacaoDTO {
    private Long id;
    private String mensagem;
    private Date dataCriacao;
    private String tipoNotificacao;
    private boolean lida;
    private UsuarioDTO usuario;

    public NotificacaoDTO(Long id, String mensagem, Date dataCriacao,
                          String tipoNotificacao, boolean lida, UsuarioDTO usuario) {
        this.id = id;
        this.mensagem = mensagem;
        this.dataCriacao = dataCriacao;
        this.tipoNotificacao = tipoNotificacao;
        this.lida = lida;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
