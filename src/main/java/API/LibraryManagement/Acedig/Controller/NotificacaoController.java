package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Data.DTO.NotificacaoDTO;
import API.LibraryManagement.Acedig.Data.Mapper;
import API.LibraryManagement.Acedig.Data.Model.Notificacao;
import API.LibraryManagement.Acedig.Service.NotificacaoService;
import org.springframework.http.ResponseEntity;
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
    List<NotificacaoDTO> getNotificacoes(@RequestParam Long usuarioId, @RequestParam boolean onlyNotLidas){
        List<Notificacao> notificacoes = onlyNotLidas ?
                notificacaoService.findNotLida(usuarioId) :
                notificacaoService.findAll(usuarioId);

        return Mapper.toNotificacaoDTOList(notificacoes);
    }

    @PatchMapping
    ResponseEntity<?> markNotificacaoAsLida(@RequestParam Long notificacaoId) {
        if(notificacaoService.findById(notificacaoId).isPresent()) {
            notificacaoService.markAsLida(notificacaoId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
