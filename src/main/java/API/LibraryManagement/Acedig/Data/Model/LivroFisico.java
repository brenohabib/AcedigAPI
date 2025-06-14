package API.LibraryManagement.Acedig.Data.Model;

import jakarta.persistence.Entity;

@Entity
public class LivroFisico extends Livro{
    private int quantidadeTotal;
    private int quantidadeDisponivel;

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
}
