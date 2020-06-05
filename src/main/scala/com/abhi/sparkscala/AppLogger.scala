package com.abhi.sparkscala

import java.util.Properties
import java.io.FileInputStream
import org.apache.log4j.LogManager
import org.apache.log4j.BasicConfigurator
import org.apache.log4j.PropertyConfigurator

trait AppLogger extends Serializable{
  
  @transient lazy val log = LogManager.getLogger(getClass().getName());
  
  def initLogger(path: String){
    
    try {
      val prop = new Properties();
      val in = new FileInputStream(path)
      prop.load(in)
      
      BasicConfigurator.resetConfiguration()
      PropertyConfigurator.configure(prop)
    } catch{
      case e : Exception => log.error("log4j properties file not found:" + e.getMessage)
    }
  }
  
  
}