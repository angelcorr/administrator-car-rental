# administrator-car-rental
Project for programming II course from the Universidad del Quindio.
This project contains the logic, visuals, models and controllers to display the project about a car rental program. This program helps create clients, vehicles to rent, manage the booking and the company information. In this case the company information is hardcoded but this can change in future iterations.

## Directory layers

### Model Layer
The model layer contains the required models/classes for the system to work.

### Service layer
The service layer contains the business logic needed for the system to add a client or a vehicle, add a booking depending on if there are vehicles available, check if a phone number is a perfect number and much more. 


### Controller and View Layers
These are related to the connection with the final user. With the Controllers and the Views we can make the users enter the information needed (Clients, Vehicles, Bookings) to be able to rent a car. With this components we can interact with the final user and make their wish come true by renting the car of their dreams.

-------------------------
Spanish:

## Pensamiento computacional

### Abstracción
   <b>¿Qué se solicita finalmente?</b>

   Un sistema con interfaz gráfica que le permita a la empresa RentCar dejar los registros manuales y gestionar sus clientes, vehículos, modalidades de alquiler, servicios adicionales y reservas. El sistema debe calcular el valor final de cada alquiler, buscar un cliente por su teléfono y decir si ese número es perfecto, y calcular los ingresos generados en un periodo de fechas.

   <b>¿Qué información es relevante?</b>

   •	Empresa: nombre comercial, NIT, dirección, teléfono, correo y página web.

   •	Cliente: nombre completo, documento, teléfono, correo, edad y fecha de registro.

   •	Vehículo: placa, marca, modelo, año, tipo y tarifa diaria.

   •	Modalidad: código, nombre, descripción, duración mínima en días, valor diario, estado (Disponible, Suspendida, Finalizada) y beneficios. La Premium además tiene tipo de cobertura, conductores adicionales y características especiales.

   •	Servicio adicional: código, nombre, descripción, precio y disponibilidad.

   •	Reserva: código, fecha de inicio, fecha de fin, cliente, vehículo, modalidad, servicios adicionales y descuento.
   
   <b>¿Cómo se agrupa la información?</b>

   •	Administrator: la empresa, que guarda las listas de todos los registros.

   •	Client, Vehicle, AdditionalService y Booking.

   •	RentalModality como clase abstracta, con tres hijas: RentalModalityEconomy, RentalModalityExecutive y RentalModalityPremium.

   •	IDiscount como interfaz, con FullPrice, PercentageDiscount y LongTermDiscount.

   •	Enumeraciones para los valores fijos: RentState, VehicleType, CoverageType, Modality y Benefit.

   <b>¿Qué funcionalidades se solicitan?</b>

1.	Registrar clientes, vehículos, modalidades y servicios adicionales.
2.	Crear reservas que relacionen cliente, vehículo, modalidad y servicios.
3.	Calcular el valor final de una reserva.
4.	Buscar un cliente por teléfono y verificar si el número es perfecto.
5.	Calcular los ingresos de las reservas en un periodo.

### Descomposición

<b>¿Como se distribuyen las funcionalidades?</b>
Mediante el uso de Servicios, para contener toda la logica relacionada al manejo de los datos que corresponde a cada Modelo. Por ejemplo se puede tomar el model Client que posee su servicio, para el manejo de la creación de nuevos Clients, su Controller y View para el manejo visual. De esta forma nos aseguramos de que cada entidad maneje los datos de forma independiente a menos que sea estrictamente necesario, como es el caso de Booking, de acceder a los datos almacenados en otras entidades para de esta forma hacer analisis correspondientes a los bookings.

<b>¿Qué debo hacer para probar las funcionalidades?<b>
En nuestro caso esperamos a llegar a un punto donde tuvieramos la forma de poder probar usando la interfaz visual el funcionamiento de cada entidad. Comenzamos con Client, para verificar que agregar un Client fuera exitoso. Con eso verificabamos que los datos estaban siendo guardados de forma exitosa y que el systema no colapsara.

### Reconocimiento de Patrones
<b>¿Qué puedo reutilizar de la solución de otros problemas?</b>
En base a lo implementado en university-library se uso el patron builder de una mejor forma ya que se evito el instaciar el objeto dos veces. Tambien se usaron metodos para la busqueda de Clients, Vehicles que eran la misma logica solo que usando datos diferentes con tipos diferentes.

### Codificación
Lo mostrado en este code base.
