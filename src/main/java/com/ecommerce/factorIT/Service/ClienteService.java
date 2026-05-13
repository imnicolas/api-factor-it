package com.ecommerce.factorIT.Service;

import com.ecommerce.factorIT.DTO.response.ClienteResponse;
import com.ecommerce.factorIT.Enum.EstadoCarrito;
import com.ecommerce.factorIT.Repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ClienteResponse> obtenerClientesVip() {
        return clienteRepository.findByEsVip(true).stream()
                .map(ClienteResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> obtenerClientesQueGanaronVip(int anio, int mes) {
        return clienteRepository.findClientesQueGanaronVipEnMes(anio, mes, EstadoCarrito.COMPLETADO).stream()
                .map(ClienteResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> obtenerClientesQuePerdieronVip(int anio, int mes) {
        return clienteRepository.findClientesVipSinComprasEnMes(anio, mes, EstadoCarrito.COMPLETADO).stream()
                .map(ClienteResponse::from)
                .toList();
    }
}
