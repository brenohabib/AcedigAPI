package API.LibraryManagement.Acedig.Data.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "livro_fisico_id")
    private LivroFisico livroFisico;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataPrevistaDevolcao;
    private Date dataDevolcao;
    private StatusEmprestimo status;
    private BigDecimal valorMulta;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LivroFisico getLivroFisico() {
        return livroFisico;
    }

    public void setLivroFisico(LivroFisico livroFisico) {
        this.livroFisico = livroFisico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataPrevistaDevolcao() {
        return dataPrevistaDevolcao;
    }

    public void setDataPrevistaDevolcao(Date dataPrevistaDevolcao) {
        this.dataPrevistaDevolcao = dataPrevistaDevolcao;
    }

    public Date getDataDevolcao() {
        return dataDevolcao;
    }

    public void setDataDevolcao(Date dataDevolcao) {
        this.dataDevolcao = dataDevolcao;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }


}
