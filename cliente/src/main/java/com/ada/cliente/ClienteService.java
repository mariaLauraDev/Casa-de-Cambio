package com.ada.cliente;

import com.ada.comum.EntidadeDuplicadaException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) throws EntidadeDuplicadaException {
        Optional<Cliente> clienteEncontrado = findByCpf(cliente.getCpf());
        if (clienteEncontrado.isPresent()) {
            throw new EntidadeDuplicadaException();
        }
        String clearCpf = cliente.getCpf().replaceAll("[\\.-]", "");
        cliente.setCpf(clearCpf);
        return clienteRepository.save(cliente);
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    public Optional<Cliente> findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public List<Cliente> findAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }

}
