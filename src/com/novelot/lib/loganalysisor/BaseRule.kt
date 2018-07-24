package com.novelot.lib.loganalysisor

import java.io.File

open abstract class BaseRule constructor(var desDirPath: String) : Rule {

    private var mStringBuilder = StringBuilder()

    private var mIntercept: Boolean = false

    override fun conform(line: String): Boolean {
        if (mIntercept) {
            if (conformBody(line)) {
                onHandleBody(line, mStringBuilder)
                return true
            } else {
                mIntercept = false
                onEnd(mStringBuilder)
                return false
            }
        } else if (conformHeader(line)) {
            mIntercept = true
            mStringBuilder.delete(0, mStringBuilder.length)
            onHandleHeader(line, mStringBuilder);
            return true
        } else {
            mIntercept = false
            return false
        }
    }

    protected abstract fun conformHeader(line: String): Boolean

    protected abstract fun conformBody(line: String): Boolean

    protected abstract fun onHandleHeader(line: String, sb: StringBuilder)

    protected abstract fun onHandleBody(line: String, sb: StringBuilder)

    open protected fun onEnd(sb: StringBuilder) {
        saveToFile()
    }

    protected fun saveToFile() {
        var desFile = File(desDirPath, Constant.ANALYSIS_FILE_NAME)

        if (!desFile.exists()) {
            desFile.parentFile.mkdirs()
            desFile.createNewFile();
        } else {
            //desFile.delete()
        }
        desFile.appendText(mStringBuilder.toString(), Charsets.UTF_8)
    }

}