package com.ecommerce.factorIT.Controller;

import com.ecommerce.factorIT.DTO.response.ClienteResponse;
import com.ecommerce.factorIT.Enum.EstadoCarrito;
import com.ecommerce.factorIT.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Clientes", description = "Consultas sobre clientes y su estado VIP")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/vip")
    @Operation(
            summary = "Listar clientes VIP actuales",
            description = "Devuelve todos los clientes que tienen actualmente el estado VIP."
    )
    public ResponseEntity<List<ClienteResponse>> obtenerClientesVip() {
        log.info("Consultando clientes VIP actuales");
        return ResponseEntity.ok(clienteService.obtenerClientesVip());
    }

    @GetMapping("/vip/nuevos")
    @Operation(
            summary = "Clientes que pasaron a ser VIP en un mes",
            description = "Devuelve los clientes cuyas compras completadas en ese mes superaron los $10.000."
    )
    public ResponseEntity<List<ClienteResponse>> obtenerClientesNuevosVip(
            @Parameter(description = "Año (ej: 2024)") @RequestParam int anio,
            @Parameter(description = "Mes (1-12)") @RequestParam @Min(1) @Max(12) int mes
    ) {
        log.info("Consultando clientes que pasaron a ser VIP en el mes {}", mes);
        return ResponseEntity.ok(clienteService.obtenerClientesQueGanaronVip(anio, mes));
    }

    @GetMapping("/vip/perdieron")
    @Operation(
            summary = "Clientes que dejaron de ser VIP en un mes",
            description = "Devuelve los clientes VIP que no realizaron compras en ese mes."
    )
    public ResponseEntity<List<ClienteResponse>> obtenerClientesPerdieroVip(
            @Parameter(description = "Año (ej: 2024)") @RequestParam int anio,
            @Parameter(description = "Mes (1-12)") @RequestParam @Min(1) @Max(12) int mes
    ) {
        log.info("Consultando clientes que perdieron VIP en el mes {}", mes);
        return ResponseEntity.ok(clienteService.obtenerClientesQuePerdieronVip(anio, mes));
    }
}
