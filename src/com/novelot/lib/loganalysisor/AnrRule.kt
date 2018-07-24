package com.novelot.lib.loganalysisor

class AnrRule(desDirPath: String) : BaseRule(desDirPath) {

    //07-18 18:12:45.489 E/ActivityManager( 2358): ANR in com.edog.car (com.edog.car/com.kaolafm.auto.home.MainActivity)

    private val STR_HEADER = ".*E/ActivityManager.*ANR in.*"
    private val STR_BODY = ".*E/ActivityManager.*"
    private val STR_SPITE = ": "

    private val mRegexStart: Regex = Regex(STR_HEADER);

    private val mRegexSegment: Regex = Regex(STR_BODY);

    override fun conformHeader(line: String) = mRegexStart.matches(line)

    override fun conformBody(line: String) = mRegexSegment.matches(line)

    override fun onHandleHeader(line: String, sb: StringBuilder) {
        sb.appendln("<h1>ANR</h1>").appendln("<hr>")
    }

    override fun onHandleBody(line: String, sb: StringBuilder) {
        sb.appendln("<h5>${subLine(line, STR_SPITE)}</h5>")
    }


    private fun subLine(line: String, keywork: String) = line.subSequence(line.indexOf(keywork) + (keywork.length - 1), line.length)

}

