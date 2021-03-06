# Compose Exercise: Retrofit Basics
- Call an external API using the Retrofit library and get the returned JSON.
- Handle loading/error/done status of the API call.
- Use query to filter the results of the API call.
- Convert JSON to Kotlin object using the Moshi library.
- Display images from remote URLs using the Coil library.
- Replace part of a string with a new value.
- Custom property on a data class.
- Currency format from number.
- Get color from hex code.
- Dropdown menu on TopAppBar (options menu).

## Reference
This is the Compose version of the codelabs [Getting data from the internet](https://codelabs.developers.google.com/codelabs/kotlin-android-training-internet-data/), [Loading and displaying images from the internet](https://codelabs.developers.google.com/codelabs/kotlin-android-training-internet-images/), and [Filtering and detail views with internet data](https://codelabs.developers.google.com/codelabs/kotlin-android-training-internet-filtering/) from the [Android Kotlin Fundamentals course](https://developer.android.com/courses/kotlin-android-fundamentals/toc).

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

// Compose Navigation
implementation "androidx.navigation:navigation-compose:2.4.0-alpha06"
```

<br />

|Property Listing|Property Details|
|:---:|:---:|
|![home](https://user-images.githubusercontent.com/67064997/130318548-b76396d4-b792-4b66-9e7c-663f95e5e059.png)|![details](https://user-images.githubusercontent.com/67064997/130318552-70340799-a2d4-4c54-a922-bac9ac3fb5a4.png)|
