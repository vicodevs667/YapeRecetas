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
- El **Repository** puede ser simulado para pruebas sin acceder a la API real.

✅ **Hace que la UI sea reactiva y más eficiente**
- Usamos **StateFlow** en lugar de LiveData para un mejor rendimiento en **Jetpack Compose**.
- Cuando los datos cambian, la UI se actualiza automáticamente sin necesidad de gestionarla manualmente.

✅ **Escalabilidad: fácil de extender y modificar**
- Si en el futuro queremos cambiar la fuente de datos de otra API o base de datos local,  
  solo modificamos el **Repository**, sin afectar la UI ni el ViewModel.

✅ **Compatible con las recomendaciones de Google**
- **MVVM** es el patrón recomendado por Google para aplicaciones Android modernas con Jetpack.

---

## **📌 Principales Decisiones de Desarrollo**
### **1️⃣ Jetpack Compose para la UI**
- Se optó por **Jetpack Compose** en lugar de XML para aprovechar las ventajas de una UI declarativa y componible.
- Otra razón es que es una librería moderna y oficial de Google para el desarrollo de UI en Android.
- Permite diseños más flexibles y reutilizables, y facilita la implementación de sus componentes.
- Se podía haber utilizado XML en los diseños con RecyclerView y ConstraintLayout, pero consideré un mejor reto el uso de esta librería para la UI.
- Se reutilizan componentes como `RecipeImage.kt` para evitar código repetido.

### **2️⃣ Repository Pattern**
- Se implementó un repositorio (`RecipeRepository`) para desacoplar el acceso a datos y facilitar las pruebas.

### **3️⃣ Dagger Hilt para Inyección de Dependencias**
- Nos permite gestionar instancias de clases sin necesidad de crearlas manualmente, facilitando la escalabilidad y testabilidad del código.
- Se usa **Dagger Hilt** para inyectar `RecipeRepository` y otros componentes sin crear instancias manualmente.
- Nos evita tener que instanciar manualmente clases como `RecipeRepository`, `ApiService`.
- Reduce la cantidad de código "boilerplate" y facilita la modularización.
- Nos permite haber integrado dependencias en el `ViewModel` integrándose perfectamente con **Jetpack Compose**.

### **4️⃣ Google Maps en `MapScreen`**
- Se implementó `GoogleMap` para mostrar la ubicación de origen de cada receta.
- Se usó un marcador personalizado cargado con `BitmapDescriptorFactory`.

---

## **📌 Pruebas Implementadas**
Se agregaron **pruebas unitarias y automatizadas** para asegurar la calidad del código.

### **🧪 Pruebas Unitarias**
- `RecipeViewModelTest.kt` → Verifica que `fetchRecipes()` para enviar los datos hacia la vista funciona correctamente.
- `RecipeRepositoryTest.kt` → Prueba la obtención de recetas desde el repositorio.

### **📱 Pruebas Automatizadas (UI Testing)**
- `HomeScreenTest.kt` → Verifica que la lista de recetas se muestra correctamente.
- `RecipeNavigationTest.kt` → Asegura que la navegación entre pantallas funciona bien.
- `MapScreenTest.kt` → Confirma que el mapa y los marcadores aparecen correctamente.
- `RecipeListScreen.kt` → Verifica que el buscador esta siendo utilizado correctamente.
- `RequestLocationPermissionTest` → Asegura que los permisos para geolocalización estan otorgados correctamente.

---

## **📌 Buenas Prácticas Aplicadas**
✅ **Código modular** → Separación clara entre UI, lógica y datos.  
✅ **Uso de `remember` y `state` en Compose** → Para una UI reactiva.  
✅ **Estructura basada en `data classes` y `StateFlow`** → Para manejar estados correctamente.  
✅ **Pruebas unitarias y de UI** → Aseguran la calidad del código.  
✅ **Uso de `LazyColumn` y listas diferidas en Compose** → Optimización del rendimiento.

---

## **📌 Novedades de la Plataforma Utilizadas**
🔥 **Jetpack Compose** → UI declarativa con estado reactivo.  
🔥 **LazyColumn con listas diferidas** → Mejora de rendimiento en la lista visual cuando se usa **Jetpack Compose**.  
🔥 **Dagger Hilt** → Inyección de dependencias simplificada.  
🔥 **Google Maps con `compose-maps`** → Integración nativa con **Jetpack Compose**.

---