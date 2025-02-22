# 📌 Yape Recetas

Este proyecto es un prototipo de aplicación de recetas de cocina 
como parte del reto técnico para **Yape Bolivia** 
desarrollada en **Kotlin** siguiendo una arquitectura **MVVM** 
con buenas prácticas y patrones de diseño para garantizar escalabilidad, testabilidad 
y mantenimiento del código.

## **🛠️ Tecnologías y Librerías Utilizadas**
- **Kotlin** → Lenguaje principal para Android.
- **Gadle** → Versión 8.2.2 para la gestión de dependencias y compatilibidad con librerías empleadas.
- **Jetpack Compose** → Para el desarrollo de la UI declarativa.
- **Dagger Hilt** → Para poder usar inyección de dependencias.
- **Retrofit** → Para el consumo de APIs.
- **Google Maps API** → Para mostrar la ubicación de origen de cada receta.
- **Coil (AsyncImage)** → Para la carga de imágenes.
- **Coroutines y Flow** → Para el manejo de datos de manera asíncrona.
- **JUnit y Mockito** → Para pruebas unitarias.
- **Compose UI Testing** → Para pruebas automatizadas en UI.

---

## **📌 Arquitectura del Proyecto**
El proyecto sigue el patrón **MVVM (Model-View-ViewModel)** con un **Repository Pattern** para gestionar los datos de manera desacoplada.
### ¿Por qué Elegimos MVVM?

Elegimos el patrón **MVVM** porque ofrece varias ventajas clave en el desarrollo de aplicaciones Android modernas:

✅ **Facilita la separación de responsabilidades**
- La **View** solo maneja la UI y delega la lógica de negocio al **ViewModel**.
- El **ViewModel** se encarga de la lógica de UI y obtiene datos desde el **Repository**.
- El **Repository** maneja el acceso a la API o base de datos.
- Asimismo el uso de **Dagger Hilt** permite la inyección de dependencias para desacoplar las clases.

✅ **Permite realizar pruebas unitarias sin depender de la UI**
- Al separar la lógica de UI en el ViewModel, podemos probarla de forma independiente con **JUnit**.
- El **Repository** puede ser simulado (`FakeRepository`) para pruebas sin acceder a la API real.

✅ **Hace que la UI sea reactiva y más eficiente**
- Usamos **StateFlow** en lugar de LiveData para un mejor rendimiento en **Jetpack Compose**.
- Cuando los datos cambian, la UI se actualiza automáticamente sin necesidad de gestionarla manualmente.

✅ **Escalabilidad: fácil de extender y modificar**
- Si en el futuro queremos cambiar la fuente de datos de otra API o base de datos local,  
  solo modificamos el **Repository**, sin afectar la UI ni el ViewModel.

✅ **Compatible con las recomendaciones de Google**
- **MVVM** es el patrón recomendado por Google para aplicaciones Android modernas con Jetpack.
