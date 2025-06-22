package API.LibraryManagement.Acedig.Data.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class LivroFisico extends Livro{
    private int quantidadeTotal;
    private int quantidadeDisponivel;
    @OneToOne(mappedBy = "livroFisico")
    private Emprestimo emprestimo;

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
}
