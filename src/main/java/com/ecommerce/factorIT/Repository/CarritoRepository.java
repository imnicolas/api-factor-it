package com.ecommerce.factorIT.Repository;

import com.ecommerce.factorIT.Enum.EstadoCarrito;
import com.ecommerce.factorIT.Model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    List<Carrito> findByClienteIdAndState(Long clienteId, EstadoCarrito state);

    /**
     * Sum of totals of completed carts for a client in a given month/year.
     */
    @Query("""
            SELECT COALESCE(SUM(c.total), 0) FROM Carrito c
            WHERE c.cliente.id = :clienteId
              AND c.state = :state
              AND YEAR(c.dateCreated) = :anio
              AND MONTH(c.dateCreated) = :mes
            """)
    BigDecimal sumTotalCompletadoByClienteAndMes(
            @Param("clienteId") Long clienteId,
            @Param("anio") int anio,
            @Param("mes") int mes,
            @Param("state") EstadoCarrito state);

    /**
     * Check if a client made any completed purchase in a given month/year.
     */
    @Query("""
            SELECT COUNT(c) FROM Carrito c
            WHERE c.cliente.id = :clienteId
              AND c.state = :state
              AND YEAR(c.dateCreated) = :anio
              AND MONTH(c.dateCreated) = :mes
            """)
    long countCompletadoByClienteAndMes(
            @Param("clienteId") Long clienteId,
            @Param("anio") int anio,
            @Param("mes") int mes,
            @Param("state") EstadoCarrito state);
}
