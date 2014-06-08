package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import utilities.PropertiesReader;

import log.Logfactory;

import database.SQLconnect;

public class Start {
	private static Logger logger = Logger.getLogger(Start.class);
	
	public static void main(String[] args){
		String type = PropertiesReader.getValue("type");
		if(type.equals("sync")){
			logger.info("同步模式开启");
			Logfactory.asyncreadstat();
		}else{
			logger.info("异步模式开启");
			Logfactory.readstat();
		}
			
	}
}
