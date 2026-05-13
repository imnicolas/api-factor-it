package com.ecommerce.factorIT.Service.strategy;

import com.ecommerce.factorIT.Enum.TipoCarrito;
import com.ecommerce.factorIT.Model.CarritoDetalle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Strategy for FECHA_ESPECIAL cart:
 * - Exactly 4 products: 25% discount
 * - More than 10 products: -100 (general) -300 (special date bonus) = -400 total
 */
@Component
public class CarritoFechaEspecialStrategy implements DescuentoStrategy {

    private static final BigDecimal DISCOUNT_4_PRODUCTS = BigDecimal.valueOf(0.75);
    private static final BigDecimal DISCOUNT_10_PLUS = BigDecimal.valueOf(400); // 100 + 300

    @Override
    public TipoCarrito getTipo() {
        return TipoCarrito.FECHA_ESPECIAL;
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
