# My-Cocktail-Application

A sample app for search products (Cocktails) and view its product details.
Here EditText is used instead of SearchView and the searched product is displayed in RecyclerView.
And the product list is written in java and the product details is in Kotlin.
The products and their details are fetched from public API.
Volley Library is used for API Integration.

Other Libraries Imported are:

dependencies {

// Volley Library for API Integration

  implementation 'com.android.volley:volley:1.2.1'
 
  
// Marcoscgdev's DialogSheet For Custom Dialogsheet

  implementation 'com.github.marcoscgdev:DialogSheet:2.0.9'
  
// Glide for Image load

  implementation 'com.github.bumptech.glide:glide:4.11.0'
  
// Dexter for handling permission requests

  implementation 'com.karumi:dexter:6.2.3'

}
