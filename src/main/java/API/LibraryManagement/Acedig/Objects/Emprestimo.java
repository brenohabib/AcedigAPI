package API.LibraryManagement.Acedig.Objects;

import API.LibraryManagement.Acedig.Enums.StatusEmprestimo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dataEmprestimo;
    private Date dataPrevistaDevolcao;
    private Date dataDevolcao;
    private StatusEmprestimo status;
    private BigDecimal valorMulta;

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
