package com.example.coding_test_app.data

data class Response(
    var meetings: List<Meeting>? = null,
    var exception: Exception? = null
)