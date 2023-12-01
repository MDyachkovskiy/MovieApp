package com.test.application.core.utils

fun <T> List<T>.convert(getValue: (T) -> String?) = this.joinToString(", "){
    getValue(it) ?: "" }