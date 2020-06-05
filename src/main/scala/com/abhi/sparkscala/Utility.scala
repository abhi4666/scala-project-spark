package com.abhi.sparkscala
import java.util.Calendar
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.hadoop.conf.Configuration
import scala.util.Try

object Utility extends AppLogger {
  
  def archieveFile(inputFilePath: String, archieveFilePath: String, fromEmail: String, toEmail: String , user: String, pwd: String)= {
    
    //Hadoop configuration
    
    val hadoopConf = new Configuration();
    hadoopConf.addResource(new Path("/etc/hadoop/conf/hdfs-site.xml"))
    hadoopConf.addResource(new Path("/etc/hadoop/conf/core-site.xml"))
     hadoopConf.addResource(new Path("/etc/hadoop/conf/hive-site.xml"))
     hadoopConf.set("fs.hdfs.impl", classOf[org.apache.hadoop.hdfs.DistributedFileSystem].getName)
      hadoopConf.set("fs.hdfs.impl", classOf[org.apache.hadoop.fs.LocalFileSystem].getName)
      val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
    val todayDate = format.format(Calendar.getInstance().getTime())
    
    val srcPath = inputFilePath
    
    val fs = FileSystem.get(hadoopConf)
    print("Before try")
    
    try {
      print("if loop starts")
      
      log.info("Checking whether dierctory exists with today date")
      
      if(fs.exists(new Path(archieveFilePath + "/" + todayDate))){
        print("if loop")
        log.info("Deleting folder if already exists with today date")
        fs.delete(new Path(archieveFilePath + "/" + todayDate),true)
        
      }
      
      log.info("if directory not exists")
      if(!fs.exists(new Path(archieveFilePath + "/" +todayDate))){
        log.info("Creating directory with todayDate")
        fs.mkdirs(new Path(archieveFilePath + "/" + todayDate))
        fs.copyFromLocalFile(new Path(srcPath), new Path(archieveFilePath + "/" + todayDate))
        
      }
      
      print("in loop")
    } catch{
      case e : Exception =>
        log.error("Exception Occured: " + e.getMessage)
    }
  }
}