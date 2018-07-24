package com.novelot.lib.loganalysisor

import java.io.File
import java.io.FileNotFoundException

class LogAnalysisor {

    private val mRules = mutableListOf<Rule>()

    private var mPriorityRule: Rule? = null

    fun addRule(rule: Rule): LogAnalysisor {
        this.mRules.add(rule)
        return this;
    }

    fun analysis(logFilePath: String, listener: AnalysisListener?) {
        var mLogFile = File(logFilePath)

        if (!mLogFile.exists()) {
            if (listener != null) {
                listener.onError(FileNotFoundException())
            }
            return
        }

        if (mLogFile.isFile) {
            //dispatch
            mLogFile.forEachLine { line ->

//                if (listener != null) {
//                    listener.onProgress()
//                }

                if (mPriorityRule != null && mPriorityRule!!.conform(line)) {
                } else {
                    mPriorityRule = null
                    for (rule in mRules) {
                        //intercept
                        if (rule.conform(line)) {
                            mPriorityRule = rule;
                            break;
                        }
                    }
                }
            }
            if (listener != null) {
                listener.onComplete()
            }
        } else {
            if (listener != null) {
                listener.onError(Exception("Log File is not a File"))
            }
            return
        }
    }

}