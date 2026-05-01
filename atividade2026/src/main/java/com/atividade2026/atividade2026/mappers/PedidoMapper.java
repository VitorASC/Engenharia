package com.atividade2026.atividade2026.mappers;

import com.atividade2026.atividade2026.domains.Pedido;
import com.atividade2026.atividade2026.domains.dtos.PedidoDTO;

public class PedidoMapper {

    public static Pedido toEntity(PedidoDTO dto) {
        Pedido obj = new Pedido();
        obj.setId(dto.getId());
        obj.setDescricao(dto.getDescricao());
        obj.setValor(dto.getValor());
        obj.setTipoFrete(dto.getTipoFrete());
        return obj;
    }

    public static PedidoDTO toDTO(Pedido obj) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(obj.getId());
        dto.setDescricao(obj.getDescricao());
        dto.setValor(obj.getValor());
        dto.setTipoFrete(obj.getTipoFrete());
        dto.setStatus(obj.getStatus());

        if (obj.getFreteStrategy() != null) {
            dto.setValorFrete(obj.calcularFrete());
        }

        return dto;
    }
}
