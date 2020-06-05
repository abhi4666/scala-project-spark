package com.abhi.sparkscala

import java.util.Properties
import java.io.FileInputStream

class Config (path : String) extends AppLogger{
  
  private var prop = new Properties()
  try {
    
    val in = new FileInputStream(path)
    prop.load(in)
  }catch {
    
    case e : Exception => log.error("Property File Loading Exception :" + e.getMessage);throw e
  }
  
  def getValue(key: String): String ={
    val returnProperty = prop.getProperty(key)
    
    //check whether key is sucessfull or not , If not exit the program 
    
    Option(returnProperty) match {
      case Some(returnedValue : String) => log.info("Key retrieved Sucessfully for : " +s"$key" + "with value" + s"$returnedValue")
      case None => log.error("Key retrival failure for key :" +s"$key"); System.exit(0)
    }
    returnProperty
  }
  
  def getHDFSsrcPath: String ={
    getValue("Hdfs Path")
  }
  
  def hdfsArchievePath: String ={
    getValue("Archieve Path")
  }
}