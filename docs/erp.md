# Especificación de requisitos de software

## Enunciado del problema

La creciente demanda de productos ecológicos ha puesto de manifiesto la necesidad de plataformas de venta accesibles y funcionales, que permitan a los usuarios adquirir productos sustentables de manera rápida, segura y eficiente. Sin embargo, los pequeños y medianos negocios dedicados a este tipo de productos a menudo carecen de un sistema de gestión digital que facilite la venta en línea, la gestión de inventario y la aplicación de descuentos. Esto limita su alcance y complica la integración con métodos de pago seguros y confiables. Un sistema que implemente estas funcionalidades permitiría a estos negocios optimizar sus operaciones, reducir errores en la administración del inventario, y mejorar la experiencia de compra de los usuarios.


## Clientes potenciales
Los clientes potenciales de esta solucion son: 
- Pequeños y medianos negocios dedicados a la venta de productos ecológicos.
- Usuarios interesados en adquirir productos ecológicos de manera rápida y segura.
- Emprendedores que buscan iniciar un negocio de venta de productos ecológicos.
- Organizaciones que buscan vender productos ecológicos en grandes cantidades.
## Solución propuesta 

El software propuesto será una plataforma de comercio electrónico orientada específicamente a la venta de productos ecológicos. Permitirá la gestión de inventario, el cálculo automático de descuentos para compras, y la integración de métodos de pago seguros. Esto facilitará el proceso de venta y compra de productos ecológicos, permitiendo a los negocios gestionar su stock y aplicar precios personalizados según el tipo de producto y promoción. La solución también proporcionará a los usuarios una experiencia de compra en línea intuitiva, en la que podrán acceder a productos individuales o paquetes con descuento de manera rápida y segura.


## Requisitos

### Gestión de Productos

- **Como** administrador de la tienda, **quiero** poder agregar, editar y eliminar productos en el sistema, **para** mantener el inventario actualizado.
- **Como** administrador, **quiero** poder clasificar productos en categorías (individuales o en paquetes) **para** facilitar la navegación de los usuarios.

### Gestión de Inventario

- **Como** administrador, **quiero** que el sistema actualice automáticamente el inventario cada vez que se realice una venta, **para** evitar sobreventas.
- **Como** administrador, **quiero** recibir alertas cuando el inventario de un producto esté bajo, **para** reabastecerlo a tiempo.

### Cálculo de Precios y Descuentos

- **Como** usuario, **quiero** ver los descuentos aplicados al precio final cuando agrego productos en paquetes al carrito, **para** conocer el ahorro en mi compra.
- **Como** administrador, **quiero** definir reglas de descuento para los productos que se vendan en paquetes, **para** atraer a más clientes.

### Gestión de Carrito de Compras

- **Como** usuario, **quiero** poder agregar productos al carrito de compras y ver el total actualizado **para** poder gestionar mi compra.
- **Como** usuario, **quiero** eliminar productos del carrito si cambio de opinión, **para** realizar la compra.
### Procesamiento de Pagos

- **Como** usuario, **quiero** poder elegir entre múltiples métodos de pago (tarjeta de crédito, transferencia bancaria, y plataformas como Mercado Pago) **para** completar la compra.
- **Como** administrador, **quiero** que el sistema registre el método de pago utilizado **para** cada compra, para poder analizar las preferencias de los clientes.

### Gestión de Usuarios y Pedidos

- **Como** usuario, **quiero** poder registrarme y acceder a una cuenta personal **para** revisar el historial de mis pedidos.
- **Como** usuario registrado, **quiero** poder ver un historial de mi pedidos, **para** rastrear el estado de los mismos desde la plataforma. 

## Arquitectura de software

El software sera una aplicacion web. Seguira la arquitectura cliente-servidor.
<br>
Tecnologias a utilizar: Spring Boot, PostgreSQL, HTML, CSS, 
