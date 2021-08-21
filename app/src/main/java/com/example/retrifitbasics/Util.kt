package com.example.retrifitbasics

// URL of the image at the endpoint is getting redirected,
// so fixed the URL before showing the image. Otherwise, mo image is shown.
fun redirectedImgSrcUrl(url: String): String {
    return url.replace( // replace part of a string
        "http://mars.jpl.nasa.gov",
        "https://mars.nasa.gov"
    )
}