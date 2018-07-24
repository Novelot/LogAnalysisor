package com.novelot.lib.loganalysisor

interface Rule {
    fun conform(line: String): Boolean
}