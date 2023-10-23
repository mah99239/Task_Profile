This is a simple Android app that demonstrates MVVM architecture, and clean arch Retrofit 2, Hilt dagger, Coroutines, and Coil. The app has two screens:

Profile screen: This screen shows the user's name, address, and a list of their albums.
Album details screen: This screen shows a list of photos in the selected album.
The app uses the following endpoints from the JSONPlaceholder API:

/users: To get a list of users.
/albums: To get a list of albums for a given user.
/photos: To get a list of photos in a given album.
The app also has a search bar that allows users to filter the photos in an album by title.

To build and run the app, follow these steps:

Clone the repository: git clone https://github.com/mah99239/Task_Profile.git
Open the project in Android Studio.
Click the Run button to run the project on your device or emulator.
Bonus points:

The app can open any image in a separate image viewer with zooming and sharing functionality.
Design patterns:

The app uses the following design patterns:

MVVM architecture: The app uses the MVVM architecture to separate the view, model, and view model layers. This makes the app more modular and easier to test.
Repository pattern: The app uses the repository pattern to abstract away the details of retrieving data from the API. This makes the app more flexible and easier to maintain.
Dependency injection: The app uses Hilt dagger for dependency injection. This makes the app more modular and easier to test.
Third-party libraries:

The app uses the following third-party libraries:

Retrofit 2: A type-safe HTTP client for Android.
Hilt dagger: A dependency injection framework for Android.
Coroutines: A library for asynchronous programming in Kotlin.
Coil: A library for image loading.
SwipeRefreshLayout: A library used for swipe layout to update data.