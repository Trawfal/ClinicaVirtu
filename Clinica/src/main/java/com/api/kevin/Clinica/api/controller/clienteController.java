package com.api.kevin.Clinica.api.controller;

import ch.qos.logback.core.net.server.Client;
import com.api.kevin.Clinica.domain.model.Cliente;
import com.api.kevin.Clinica.domain.repository.ClienteRepository;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class clienteController {
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listCustomer() {
        return clienteRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente addCustomer(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{clienteID}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long clienteID, @RequestBody Cliente novoCliente) {
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }

        Cliente clienteExistente = clienteRepository.findById(clienteID).orElse(null);
        if (clienteExistente != null) {
            clienteExistente.setNome(novoCliente.getNome());
            clienteExistente.setCpf(novoCliente.getCpf());
            clienteExistente.setEmail(novoCliente.getEmail());
            clienteExistente.setTelefone(novoCliente.getTelefone());
            clienteExistente.setCidade(novoCliente.getCidade());
            clienteExistente.setTexto(novoCliente.getTexto());

            Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
            return ResponseEntity.ok(clienteAtualizado);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @DeleteMapping("/{clienteID}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long clienteID) {
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(clienteID);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{clienteID}")
    public ResponseEntity<Cliente> atualizarTextoCliente(@PathVariable Long clienteID, @RequestBody Map<String, Object> atributos) {
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }

        Cliente clienteExistente = clienteRepository.findById(clienteID).orElse(null);
        if (clienteExistente != null) {
            Object novoTextoObjeto = atributos.get("texto");
            if (novoTextoObjeto instanceof String) {
                String novoTexto = (String) novoTextoObjeto;
                clienteExistente.setTexto(novoTexto);
                // Atualize outros atributos conforme necessário

                Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
                return ResponseEntity.ok(clienteAtualizado);
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

