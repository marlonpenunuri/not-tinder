package com.example.pc.photoshelterapp.data

sealed class ParseResult

class Success<T>(val data: T): ParseResult()

class Error(val errorMessage: String): ParseResult()

