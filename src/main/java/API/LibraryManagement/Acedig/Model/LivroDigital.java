package API.LibraryManagement.Acedig.Model;

import jakarta.persistence.Entity;

@Entity
public class LivroDigital extends Livro{
    private int quantidadePaginas;
    private double tamanhoArquivo;
    private TipoLivro tipoLivro;

    public int getQuantidadePaginas() {
        return quantidadePaginas;
    }

    public void setQuantidadePaginas(int quantidadePaginas) {
        this.quantidadePaginas = quantidadePaginas;
    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public TipoLivro getTipoLivro() {
        return tipoLivro;
    }

    public void setTipoLivro(TipoLivro tipoLivro) {
        this.tipoLivro = tipoLivro;
    }
}
