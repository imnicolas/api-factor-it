package com.ecommerce.factorIT.Service.strategy;

import com.ecommerce.factorIT.Enum.TipoCarrito;
import com.ecommerce.factorIT.Model.CarritoDetalle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Strategy for COMUN cart:
 * - Exactly 4 products: 25% discount
 * - More than 10 products: -100 discount
 */
@Component
public class CarritoComunStrategy implements DescuentoStrategy {

    private static final BigDecimal DISCOUNT_4_PRODUCTS = BigDecimal.valueOf(0.75);
    private static final BigDecimal DISCOUNT_10_PLUS = BigDecimal.valueOf(100);

    @Override
    public TipoCarrito getTipo() {
        return TipoCarrito.COMUN;
    }

    @Override
    public BigDecimal calcularTotal(BigDecimal montoBruto, List<CarritoDetalle> detalles) {
        int total = totalProductos(detalles);

        if (total == 4) {
            return montoBruto.multiply(DISCOUNT_4_PRODUCTS);
        }
        if (total > 10) {
            return montoBruto.subtract(DISCOUNT_10_PLUS).max(BigDecimal.ZERO);
        }
        return montoBruto;
    }
}
