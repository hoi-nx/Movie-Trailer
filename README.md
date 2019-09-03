# Movie-Trailer
## App
![](images/app_intro.mp4)
![](images/intro.gif)
## Technology
![alt text](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

API <https://www.themoviedb.org/documentation/api>

Kotlin: <https://kotlinlang.org/docs/reference/>

Data binding: <https://developer.android.com/topic/libraries/data-binding/>

RxJava 2, RxAndroid: <https://github.com/ReactiveX/RxJava>

Coroutines: <https://kotlinlang.org/docs/reference/coroutines.html>

Retrofit 2: <https://github.com/square/retrofit>

Gson: <https://github.com/google/gson>

Glide: <https://github.com/bumptech/glide>

Koin: <https://github.com/InsertKoinIO/koin>

Android X: <https://developer.android.com/topic/libraries/support-library/androidx-overview>


===> Android Architecture Component

Lifecycle: <https://developer.android.com/topic/libraries/architecture/lifecycle>

View Model: <https://developer.android.com/topic/libraries/architecture/viewmodel>

Live data: <https://developer.android.com/topic/libraries/architecture/livedata.html>

Room: <https://developer.android.com/topic/libraries/architecture/room.html>

Navigation: <https://developer.android.com/guide/navigation>

Architecture: <https://github.com/googlesamples/android-architecture-components>

## API keys

You need to supply API / client keys for the various services the
app uses. That is currently [TMDb](https://www.themoviedb.org/documentation/api) and [Google-service.json](https://firebase.google.com) (for Crashlytics). You can find information about
how to gain access via the relevant links.

When you obtain the keys, you can provide them to the app by putting the following in the
`config.properties` file in your user home:

```
# Get this from TMDb
CLIENT_ID = <INSERT_YOUR_KEY>

# Get this from Crashlytics
Copy your google-service.json to folder app
```
 That file is typically found at `/app/config.properties` or in the project directory `Movie-Trailer/app/config.properties`
