package com.atividade2026.atividade2026.services;

import com.atividade2026.atividade2026.domains.Pedido;
import com.atividade2026.atividade2026.domains.dtos.PedidoDTO;
import com.atividade2026.atividade2026.mappers.PedidoMapper;
import com.atividade2026.atividade2026.repositories.PedidoRepository;
import com.atividade2026.atividade2026.services.exceptions.ObjectNotFoundException;
import com.atividade2026.atividade2026.strategy.FreteAereoStrategy;
import com.atividade2026.atividade2026.strategy.FreteSedex;
import com.atividade2026.atividade2026.strategy.FreteTerrestreStrategy;
import com.atividade2026.atividade2026.strategy.FreteStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Page<PedidoDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(pedido -> {
                    aplicarStrategy(pedido);
                    return PedidoMapper.toDTO(pedido);
                });
    }

    public PedidoDTO findById(Integer id) {
        Pedido obj = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! ID: " + id));
        aplicarStrategy(obj);
        return PedidoMapper.toDTO(obj);
    }

    @Transactional
    public PedidoDTO create(PedidoDTO dto) {
        Pedido obj = PedidoMapper.toEntity(dto);
        obj.setStatus("AGUARDANDO_PAGAMENTO");
        aplicarStrategy(obj);
        Pedido saved = repository.save(obj);
        aplicarStrategy(saved);
        return PedidoMapper.toDTO(saved);
    }

    @Transactional
    public PedidoDTO update(Integer id, PedidoDTO dto) {
        Pedido obj = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! ID: " + id));

        obj.setDescricao(dto.getDescricao());
        obj.setValor(dto.getValor());
        obj.setTipoFrete(dto.getTipoFrete());

        aplicarStrategy(obj);
        Pedido saved = repository.save(obj);
        aplicarStrategy(saved);
        return PedidoMapper.toDTO(saved);
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ObjectNotFoundException("Pedido não encontrado! ID: " + id);
        }
        repository.deleteById(id);
    }

    @Transactional
    public PedidoDTO pagar(Integer id) {
        Pedido obj = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! ID: " + id));
        obj.pagar();
        Pedido saved = repository.save(obj);
        aplicarStrategy(saved);
        return PedidoMapper.toDTO(saved);
    }

    @Transactional
    public PedidoDTO enviar(Integer id) {
        Pedido obj = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! ID: " + id));
        obj.enviar();
        Pedido saved = repository.save(obj);
        aplicarStrategy(saved);
        return PedidoMapper.toDTO(saved);
    }

    @Transactional
    public PedidoDTO cancelar(Integer id) {
        Pedido obj = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! ID: " + id));
        obj.cancelar();
        Pedido saved = repository.save(obj);
        aplicarStrategy(saved);
        return PedidoMapper.toDTO(saved);
    }

    private void aplicarStrategy(Pedido pedido) {
        if (pedido.getTipoFrete() == null) return;

        FreteStrategy strategy;
        switch (pedido.getTipoFrete().toUpperCase()) {
            case "AEREO":
                strategy = new FreteAereoStrategy();
                break;
            case "SEDEX":
                strategy = new FreteSedex();
                break;
            case "TERRESTRE":
            default:
                strategy = new FreteTerrestreStrategy();
                break;
        }
        pedido.setFreteStrategy(strategy);
    }
}
