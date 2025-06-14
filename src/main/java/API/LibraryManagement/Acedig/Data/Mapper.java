package API.LibraryManagement.Acedig.Data;

import API.LibraryManagement.Acedig.Data.DTO.NotificacaoDTO;
import API.LibraryManagement.Acedig.Data.DTO.UsuarioDTO;
import API.LibraryManagement.Acedig.Data.Model.Notificacao;
import API.LibraryManagement.Acedig.Data.Model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.isAtivo(),
                usuario.getDataCadastro()
        );
    }

    public static NotificacaoDTO toNotificacaoDTO(Notificacao notificacao) {
        if (notificacao == null) {
            return null;
        }

        return new NotificacaoDTO(
                notificacao.getId(),
                notificacao.getMensagem(),
                notificacao.getDataCriacao(),
                notificacao.getTipoNotificacao().toString(),
                notificacao.isLida(),
                toUsuarioDTO(notificacao.getUsuario())
        );
    }

    public static List<NotificacaoDTO> toNotificacaoDTOList(List<Notificacao> notificacoes) {
        return notificacoes.stream()
                .map(API.LibraryManagement.Acedig.Data.Mapper::toNotificacaoDTO)
                .collect(Collectors.toList());
    }
}
