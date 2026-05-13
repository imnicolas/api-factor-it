package com.ecommerce.factorIT.Service.strategy;

import com.ecommerce.factorIT.Enum.TipoCarrito;
import com.ecommerce.factorIT.Model.CarritoDetalle;

import java.math.BigDecimal;
import java.util.List;

public interface DescuentoStrategy {

    TipoCarrito getTipo();

    BigDecimal calcularTotal(BigDecimal montoBruto, List<CarritoDetalle> detalles);

    default int totalProductos(List<CarritoDetalle> detalles) {
        return detalles.stream().mapToInt(CarritoDetalle::getCantidad).sum();
    }
}
