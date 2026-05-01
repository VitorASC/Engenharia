package com.atividade2026.atividade2026.resources;

import com.atividade2026.atividade2026.domains.dtos.PedidoDTO;
import com.atividade2026.atividade2026.services.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<Page<PedidoDTO>> findAll(Pageable pageable) {
        Page<PedidoDTO> page = service.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Integer id) {
        PedidoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> create(@Valid @RequestBody PedidoDTO dto) {
        PedidoDTO created = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody PedidoDTO dto) {
        PedidoDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<PedidoDTO> pagar(@PathVariable Integer id) {
        PedidoDTO dto = service.pagar(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/enviar")
    public ResponseEntity<PedidoDTO> enviar(@PathVariable Integer id) {
        PedidoDTO dto = service.enviar(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<PedidoDTO> cancelar(@PathVariable Integer id) {
        PedidoDTO dto = service.cancelar(id);
        return ResponseEntity.ok(dto);
    }
}
