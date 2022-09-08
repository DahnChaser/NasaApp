# NasaApp

Read me
This project allows users to scroll through the list of Milky Way images taken in 2017.


3rd party libraries used
* Glide - a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
* Retrofit - A type-safe HTTP client for Android and Java developed by square
* GSON - Java/Kotlin library that can be used to convert Java/Kotlin Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java/Kotlin object.
* Shimmerview - by facebook for recyclerview loading indicator
Architecture
- Uses Kotlin
- The app uses google navigation graph to effectively handle navigations within the app
- MVVM architecture which takes advantage of view model to store and manage UI-related data in a lifecycle conscious way
- The app is modular eg networking_module which makes it easy to group together modules for different functionalities hence enhancing code
reusing between different projects
- View binding which is a feature that allows a developer to easily write code that interacts with views instead of the traditional findViewByID
-The app uses mutableliveData objects for passing data as observable objects
