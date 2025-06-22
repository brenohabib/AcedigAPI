package API.LibraryManagement.Acedig.Controller;

import API.LibraryManagement.Acedig.Data.DTO.NotificacaoDTO;
import API.LibraryManagement.Acedig.Data.Mapper;
import API.LibraryManagement.Acedig.Data.Model.Notificacao;
import API.LibraryManagement.Acedig.Service.EmprestimoService;
import API.LibraryManagement.Acedig.Service.NotificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;
    private final EmprestimoService emprestimoService;

    NotificacaoController(NotificacaoService notificacaoService, EmprestimoService emprestimoService) {
        this.notificacaoService = notificacaoService;
        this.emprestimoService = emprestimoService;
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
    @PostMapping("/notificar-vencimentos")
    public ResponseEntity<String> notificarVencimentosHoje() {
        emprestimoService.notificarEmprestimosVenceremHoje();
        return ResponseEntity.ok("Notificações de vencimento enviadas");
    }

    @PostMapping("/notificar-atrasos")
    public ResponseEntity<String> notificarAtrasos() {
        emprestimoService.notificarEmprestimosEmAtraso();
        return ResponseEntity.ok("Notificações de atraso enviadas");
    }
}
