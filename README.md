# GLogs Lite

GLogs Lite is my submission app for [Belajar Membuat Aplikasi Android dengan Jetpack Compose](https://www.dicoding.com/academies/445) class from Dicoding. 

## Table of Contents
- [Screenshots](#screenshots)
- [Tech Stacks](#tech-stacks)
- [Setup](#setup)

## Screenshots
<table>
  <tbody>
    <tr>
      <td><img src="assets/screenshot/home.png?raw=true"/></td>
      <td><img src="assets/screenshot/library.png?raw=true"/></td>
      <td><img src="assets/screenshot/search.png?raw=true"/></td>
      <td><img src="assets/screenshot/detail.png?raw=true"/></td>
    </tr>
  </tbody>
</table>

## Tech Stacks

List of technologies used in this app.
- Language: [Kotlin](https://kotlinlang.org)
- Architecture: [Android App Architecture](https://developer.android.com/topic/architecture)
- User Interface: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- Concurrency: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- Local Database: [Room](https://developer.android.com/training/data-storage/room)
- Networking: [OkHttp](https://square.github.io/okhttp) & [Retrofit](https://square.github.io/retrofit)
- Pagination: [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- Image Loading: [Coil](https://coil-kt.github.io)
- Theme: [Material Design 3](https://m3.material.io/)


## Setup
To build this project, you need the latest stable version of [Android Studio](https://developer.android.com/studio).

1. Clone or download the project and open it in Android Studio.
2. Create a `local.properties` file in the project root folder if it doesn't exist.
3. Add `API_KEY` and `BASE_URL` in the file as shown below.

```android
...

API_KEY = "key here"
BASE_URL = "https://www.giantbomb.com/api/"
```

4. Get `API_KEY` from [Giant Bomb](https://www.giantbomb.com/).
5. Sync the project with Gradle and run the app on an Android emulator or a physical Android device.
