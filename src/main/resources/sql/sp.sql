CREATE OR REPLACE PROCEDURE take_random_pizza_order(in customer_id UUID, IN order_method TEXT, OUT order_taken BOOLEAN)
AS
$$
DECLARE
id_random_pizza UUID;
    price_random_pizza DOUBLE PRECISION;
    price_with_discount DOUBLE PRECISION;
    with_errors BOOLEAN DEFAULT FALSE;
    last_order_id UUID;
BEGIN
BEGIN
SELECT pizza_id, pizza_price
INTO id_random_pizza, price_random_pizza
FROM main.pizza
WHERE pizza_available = 1
ORDER BY RANDOM()
    LIMIT 1;

price_with_discount := price_random_pizza - (price_random_pizza * 0.20);

BEGIN
            -- Realizar los inserts dentro del bloque BEGIN...END
BEGIN
                -- Insertar en la tabla pizza_order
INSERT INTO pizza_order (customer_id, order_date, order_total, order_method, order_additional_notes)
VALUES (customer_id, CURRENT_DATE, price_with_discount, order_method, '20% OFF PIZZA RANDOM PROMOTION')
    RETURNING order_id INTO last_order_id;

-- Insertar en la tabla order_item
INSERT INTO order_item (order_id, pizza_id, order_quantity, order_price)
VALUES (last_order_id, id_random_pizza, 1, price_random_pizza);
EXCEPTION WHEN OTHERS THEN
                with_errors := TRUE;
                -- Deshacer la transacción en caso de error
ROLLBACK;
END;

            IF with_errors THEN
                order_taken := FALSE;
ELSE
                order_taken := TRUE;
END IF;
END;
EXCEPTION WHEN OTHERS THEN
        with_errors := TRUE;
END;

    IF with_errors THEN
        order_taken := FALSE;
END IF;
END;
$$ LANGUAGE plpgsql;
