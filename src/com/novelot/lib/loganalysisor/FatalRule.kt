package com.novelot.lib.loganalysisor

class FatalRule(desDirPath: String) : BaseRule(desDirPath) {

    //07-20 17:15:26.705 19571-19571/com.edog.car E/AndroidRuntime: FATAL EXCEPTION: main
    private val STR_HEADER = ".*E/AndroidRuntime.*FATAL EXCEPTION.*"

    //   Process: com.edog.car, PID: 19571
    private val STR_BODY_PROCESS = ".*Process:.*PID:.*"

    //java.lang.RuntimeException
    private val STR_BODY_JAVE_EXCEPTION = "^\\s*java\\.lang\\..*"

    //Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'int com.kaolafm.auto.view.CustomerDi
    private val STR_BODY_CAUSED_BY = ".*Caused by:.*"

    //at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:616)
    private val STR_BODY_AT = "^\\s*at .*"

    private val STR_SPITE = ": "

    private val mRegexStart: Regex = Regex(STR_HEADER);

    private val mRegexProcess: Regex = Regex(STR_BODY_PROCESS);

    private val mRegexJaveException: Regex = Regex(STR_BODY_JAVE_EXCEPTION);

    private val mRegexCausedBy: Regex = Regex(STR_BODY_CAUSED_BY);

    private val mRegexAt: Regex = Regex(STR_BODY_AT);


    override fun conformHeader(line: String) = mRegexStart.matches(line)

    override fun conformBody(line: String) = mRegexProcess.matches(line) || mRegexJaveException.matches(line) || mRegexCausedBy.matches(line) || mRegexAt.matches(line)


    override fun onHandleHeader(line: String, sb: StringBuilder) {
        sb.appendln("<h1>Fatal</h1>").appendln("<hr>")
        sb.appendln("<h5>${line}</h5>")
    }

    override fun onHandleBody(line: String, sb: StringBuilder) {
        sb.appendln("<h5>${line}</h5>")
    }
}

