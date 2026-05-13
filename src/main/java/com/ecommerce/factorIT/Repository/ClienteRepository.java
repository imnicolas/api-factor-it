package com.ecommerce.factorIT.Repository;

import com.ecommerce.factorIT.Enum.EstadoCarrito;
import com.ecommerce.factorIT.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByDni(String dni);

    List<Cliente> findByEsVip(boolean esVip);

    /**
     * Clients who became VIP in a given month: those whose completed cart totals exceed 10,000 that month.
     */
    @Query("""
            SELECT DISTINCT c.cliente FROM Carrito c
            WHERE c.state = :state
              AND YEAR(c.dateCreated) = :anio
              AND MONTH(c.dateCreated) = :mes
            GROUP BY c.cliente
            HAVING SUM(c.total) > 10000
            """)
    List<Cliente> findClientesQueGanaronVipEnMes(@Param("anio") int anio, @Param("mes") int mes, @Param("state") EstadoCarrito state);

    /**
     * VIP clients who made no completed purchases in the given month (lost VIP candidates).
     */
    @Query("""
            SELECT cl FROM Cliente cl
            WHERE cl.esVip = true
              AND cl.id NOT IN (
                  SELECT c.cliente.id FROM Carrito c
                  WHERE c.state = :state
                    AND YEAR(c.dateCreated) = :anio
                    AND MONTH(c.dateCreated) = :mes
              )
            """)
    List<Cliente> findClientesVipSinComprasEnMes(@Param("anio") int anio, @Param("mes") int mes, @Param("state") EstadoCarrito state);
}
