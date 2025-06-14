package API.LibraryManagement.Acedig.Service;

import API.LibraryManagement.Acedig.Data.Model.Livro;
import API.LibraryManagement.Acedig.Repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class CommonLivroService<T extends Livro> {

    protected final LivroRepository<T> repository;

    public CommonLivroService(LivroRepository<T> repository) {
        this.repository = repository;
    }

    public Page<T> find(String termo, Pageable pageable) {
        if (termo == null || termo.trim().isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.findByTituloContainingOrAutorContaining(termo, termo, pageable);
    }

    public List<T> findWithFilter(String titulo, String autor, String editora, Boolean ativo) {
        return repository.findByTituloOrAutorOrEditoraOrAtivo(titulo, autor, editora, ativo);
    }

    public T create(T livro) {
        return repository.save(livro);
    }

    public T update(Long id, T livroAtualizado) {
        T livro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));

        dataToUpdate(livro, livroAtualizado);

        return repository.save(livro);
    }

    public void remove(Long id) {
        T livro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        livro.setAtivo(false);
        repository.save(livro);
    }

    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    protected abstract void dataToUpdate(T livroExistente, T livroAtualizado);
}