package com.api.kevin.Clinica.api.controller;

import com.api.kevin.Clinica.domain.model.Cliente;
import com.api.kevin.Clinica.domain.model.Pessoa;
import com.api.kevin.Clinica.domain.repository.ClienteRepository;
import com.api.kevin.Clinica.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/clientes")
public class clienteController {
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listCliente() {
        return clienteRepository.findAll();
    }
    @GetMapping("/redirect")
    public String redirectBytipoDePessoa(Pessoa pessoa){
        if (pessoa.getTipoDePessoa().equals("1506")) {
            return "redirect:http://localhost:3000/AreaMedica";
        } else if (pessoa.getTipoDePessoa().equals("8855")) {
            return "redirect:http://localhost:3000/Consultas";
        } else {
            return "senha incorreta";
        }
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
            clienteExistente.setNomeDosPais(novoCliente.getNomeDosPais());
            clienteExistente.setAltura(novoCliente.getAltura());
            clienteExistente.setPeso(novoCliente.getPeso());
            clienteExistente.setDataNascimento(novoCliente.getDataNascimento());


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
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long clienteID, @RequestBody Map<String, Object> atributos) {
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }

        Cliente clienteExistente = clienteRepository.findById(clienteID).orElse(null);
        if (clienteExistente != null) {
            if (atributos.containsKey("texto")) {
                String novoTexto = (String) atributos.get("texto");
                clienteExistente.setTexto(novoTexto);
            }
            if (atributos.containsKey("receitas")) {
                String novaReceita = (String) atributos.get("receitas");
                clienteExistente.setReceitas(novaReceita);
            }
            if (atributos.containsKey("exames")) {
                String novoExame = (String) atributos.get("exames");
                clienteExistente.setTexto(novoExame);
            }

            Cliente clienteAtualizado = clienteRepository.save(clienteExistente);
            return ResponseEntity.ok(clienteAtualizado);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}

