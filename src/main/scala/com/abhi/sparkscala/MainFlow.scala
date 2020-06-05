package com.abhi.sparkscala
import scala.io.Source
import java.util.Date
import java.text.SimpleDateFormat
import scala.collection.mutable.ArrayBuffer
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import com.abhi.sparkscala._
import java.nio.file.Files
import java.nio.file.Paths
import java.io.FileInputStream
import org.apache.tools.ant.types.FileList.FileName

object MainFlow extends AppLogger {

  def triggerCheck(triggerFile: String): Boolean = {

    println(triggerFile)

    val exists = Files.exists(Paths.get(triggerFile))
    return exists
  }

  def fileValidation(ArchievePath: String, SrcPath: String, fromEmail: String, toEmail: String, filesPath: String, user: String, pwd: String): Int = {

    var missingFiles = ArrayBuffer[String]()

    try {

      val fileStream = new FileInputStream(filesPath)
      println(fileStream)

      val lines = Source.fromInputStream(fileStream).getLines()

      lines.foreach(x => {
        val fileName = SrcPath + x
        if (!Files.exists(Paths.get(fileName))) {

          missingFiles += x
        }
      })

      println("Size :" + missingFiles.size)
      if ((missingFiles.size == 0)) {

        println("No missing files")

      } else {

        log.error("File Validation Failed ")
        log.error("missingFiles : " + missingFiles.toString().substring(12))
      }
    } //end of try 
    
    catch {
      case e : Exception => log.error("Exception occured :" + e.getMessage)
    } finally {
      log.info("calling arcieveFile() Method")
      
      Utility.archieveFile(SrcPath, ArchievePath, fromEmail, toEmail, user, pwd)
    }
    return missingFiles.size
  }

//  def main(args: Array[String]): Unit = {
//  initLogger(args(0))
// 
//  val config = new Config(args(1))
//  val archievePath = config.getArchievePath
//  val srcPath = config.getSrcPath
//  val filelist = args(2)
//  
//  val boolean = triggerCheck(triggerFile)
//  if(boolean) {
//    log.info("Calling file validation Method")
//  }
//}
}

   
