package com.example.dictonaryandroidapp.model

//typealias Root = List<WordResult>;


data class WordResult(
    val word: String,
    val phonetic: String,
    val phonetics: List<Phonetic>,
    val meanings: List<Meaning>,
    val license: License2,
    val sourceUrls: List<String>,
)


data class Phonetic(
    val text: String,
    val audio: String,
    val sourceUrl: String,
    val license: License,
)


data class License(
    val name: String,
    val url: String,
)


data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>,
    val synonyms: List<String>,
    val antonyms: List<Any?>,
)


data class Definition(
    val definition: String,
    val synonyms: List<Any?>,
    val antonyms: List<Any?>,
)



data class License2(
    val name: String,
    val url: String,
)
