package com.example.pc.photoshelterapp.data

/**
 *Shared class for Api call results
 **/

sealed class ParseResult

class Success<T>(val data: T): ParseResult()

class Error(val errorMessage: Int): ParseResult()

