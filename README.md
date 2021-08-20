# Compose Exercise: Retrofit Basics
- Call an external API using the Retrofit library and get the returned JSON.
- Handle loading/error/done status of the API call.
- Use query to filter the results of the API call.
- Convert JSON to Kotlin object by the Moshi library.
- Display images from remote URLs by the Coil library.
- Replace part of a string with a new value.
- Custom property on a data class.

## Dependencies
```
<uses-permission android:name="android.permission.INTERNET" />
```

```
// Moshi
implementation "com.squareup.moshi:moshi-kotlin:1.12.0"

// Retrofit with Moshi Converter
implementation "com.squareup.retrofit2:retrofit:2.9.0"
implementation "com.squareup.retrofit2:converter-moshi:2.9.0"

// Compose Coil
implementation 'io.coil-kt:coil-compose:1.3.2'

// Compose ViewModel
implementation "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"

// Compose LiveData
implementation "androidx.compose.runtime:runtime-livedata:1.0.1"
```
