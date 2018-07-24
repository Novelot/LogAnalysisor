package com.novelot.lib.loganalysisor

import java.lang.Exception

interface AnalysisListener {
    fun onError(exception: Throwable)
    fun onComplete()
}