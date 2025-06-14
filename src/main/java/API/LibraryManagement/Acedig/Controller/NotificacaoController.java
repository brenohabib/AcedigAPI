package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Model.Notificacao;
import API.LibraryManagement.Acedig.Service.NotificacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping
    List<Notificacao> getNotificacoes(@RequestParam Long usuarioId, @RequestParam boolean onlyNotLidas){
        return onlyNotLidas ? notificacaoService.findNotLida(usuarioId) : notificacaoService.find(usuarioId);
    }

    @PutMapping
    void markNotificacaoAsLida(@RequestParam Long notificacaoId) {
        notificacaoService.markAsLida(notificacaoId);
    }
}
