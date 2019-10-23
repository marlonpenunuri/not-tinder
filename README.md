# Quests

## Libraries

 - Dagger 2
 - DataBinding
 - Coroutines
 - Retrofit2
 - Picasso
 - OkHttp
 - Navigation Controller
 - LiveData

##  Clean Architecture
The architecture, combined with the SOLID principles offer an uncoupled, easy-to-test, and readable code. It mainly consists of three layers: 

	 1. Presentation or UI Layer
	 2. Domain or Business Layer
	 3. Data Layer

The **Presentation Layer**, composed by the everything the user interacts with, made use of the MVVM pattern, Navigation Controlller to offer navigation tools and a graph to show a visual representation of the screen's interconnectivity, and the use of LiveData in conjunction with a View Model to retain and observe UI data.

On the other side, the **Domain Layer** wasn't fully developed because of the lack of defined usecases and absence of API requests which demanded validation. Still, decided to keep it just to showcase a blueprint of the architecture.

Lastly, the **Data Layer** takes care of the communication with the APIs and determining the source of truth between local and remote datasources. 

>**NOTE:** Dagger 2 implementation was showcased in order to fulfill SOLID's "Dependency Injection" principle, which helps you manage dependencies in a more efficient way and structure the code in a way which makes testing much easier.





