# GLogs Lite

Android app for the final submission of Dicoding [Belajar Membuat Aplikasi Android dengan Jetpack Compose](https://www.dicoding.com/academies/445) class.

## Table of Contents
- [Screenshot](#screenshot)
- [Setup](#setup)
- [Tech Stack](#tech-stack)

## Screenshot
| Splash Screen | Home | Library |
| --- | --- | --- |
| ![](assets/splash.png?raw=true) | ![](assets/home.png?raw=true) | ![](assets/library.png?raw=true) |

| Search | Detail |
| --- | --- |
| ![](assets/search.png?raw=true) | ![](assets/detail.png?raw=true) |

## Setup
- create `local.properties` file in [root folder](./) if not exists.
- Add `API_KEY` and `BASE_URL` in the file as shown below.


```android
...

API_KEY = "key here"
BASE_URL = "https://www.giantbomb.com/api/"
```

- Get `API_KEY` from [Giant Bomb](https://www.giantbomb.com/)

## Tech Stack

List of technologies used in this app.

### Languages
- [Kotlin](https://kotlinlang.org)

### Libraries
- [Jetpack Compose](https://developer.android.com/jetpack/compose/documentation)
- [MVVM](https://developer.android.com/topic/architecture)
- [Android Jetpack](https://developer.android.com/jetpack/)
- [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- [Coil](https://github.com/coil-kt/coil)
- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [OkHttp](https://github.com/square/okhttp)
- [Retrofit](https://github.com/square/retrofit)
- [Room](https://developer.android.com/training/data-storage/room)

### Tools
- [ADB Idea](https://plugins.jetbrains.com/plugin/7380-adb-idea)
- [Postman](https://www.getpostman.com)
- [Scrcpy](https://github.com/Genymobile/scrcpy)

### Design
- [Material Design](https://material.io)
- [Material Icons](https://material.io/resources/icons/)
