import com.novelot.lib.loganalysisor.AnalysisListener
import com.novelot.lib.loganalysisor.AnrRule
import com.novelot.lib.loganalysisor.FatalRule
import com.novelot.lib.loganalysisor.LogAnalysisor

fun main(args: Array<String>) {

    println("--- 日志分析器 ---")

    var logFilePath = "/Users/v/Desktop/奇瑞anr"              //Constant.ANR_FILE_PATH
    var desDirPath = "/Users/v/Desktop/log-analysisor/"         //"/sdcard/log-analysisor/"

    LogAnalysisor()
            .addRule(FatalRule(desDirPath))
            .addRule(AnrRule(desDirPath))
            .analysis(logFilePath, object : AnalysisListener {
                override fun onError(exception: Throwable) {
                    println("Log分析出现错误:" + exception)
                }

                override fun onComplete() {
                    println("Log分析完成.")
                }

            })

    println("--- End ---")
}




