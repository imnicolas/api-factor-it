package com.ecommerce.factorIT.Service.strategy;

import com.ecommerce.factorIT.Enum.TipoCarrito;
import com.ecommerce.factorIT.Model.CarritoDetalle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Strategy for VIP cart:
 * - Exactly 4 products: 25% discount
 * - More than 10 products: cheapest product free + -100 (general) -500 (VIP bonus) = -600 + cheapest
 */
@Component
public class CarritoVipStrategy implements DescuentoStrategy {

    private static final BigDecimal DISCOUNT_4_PRODUCTS = BigDecimal.valueOf(0.75);
    private static final BigDecimal DISCOUNT_10_PLUS = BigDecimal.valueOf(600); // 100 + 500

    @Override
    public TipoCarrito getTipo() {
        return TipoCarrito.VIP;
    }

    @Override
    public BigDecimal calcularTotal(BigDecimal montoBruto, List<CarritoDetalle> detalles) {
        int total = totalProductos(detalles);

        if (total == 4) {
            return montoBruto.multiply(DISCOUNT_4_PRODUCTS);
        }
        if (total > 10) {
            BigDecimal cheapestPrice = detalles.stream()
                    .map(CarritoDetalle::getPrecioUnitario)
                    .min(BigDecimal::compareTo)
                    .orElse(BigDecimal.ZERO);

            return montoBruto
                    .subtract(cheapestPrice)
                    .subtract(DISCOUNT_10_PLUS)
                    .max(BigDecimal.ZERO);
        }
        return montoBruto;
    }
}
