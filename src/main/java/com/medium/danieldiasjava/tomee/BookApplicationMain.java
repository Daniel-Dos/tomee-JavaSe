package com.medium.danieldiasjava.tomee;

import java.io.File;
import java.util.concurrent.Semaphore;

import org.apache.tomee.bootstrap.Archive;
import org.apache.tomee.bootstrap.Server;

import com.medium.danieldiasjava.tomee.annotations.Repository;
import com.medium.danieldiasjava.tomee.annotations.Service;
import com.medium.danieldiasjava.tomee.application.BookApplicationApi;
import com.medium.danieldiasjava.tomee.banner.Banner;
import com.medium.danieldiasjava.tomee.controller.BookController;
import com.medium.danieldiasjava.tomee.dto.BookDTO;
import com.medium.danieldiasjava.tomee.dto.Errors;
import com.medium.danieldiasjava.tomee.dto.ResponseDTO;
import com.medium.danieldiasjava.tomee.dto.mapper.BookDTOMapper;
import com.medium.danieldiasjava.tomee.events.BookStoreEvents;
import com.medium.danieldiasjava.tomee.model.Book;
import com.medium.danieldiasjava.tomee.produces.EmbeddedStorageManagerProducer;
import com.medium.danieldiasjava.tomee.produces.LoggerProduce;
import com.medium.danieldiasjava.tomee.repository.BookEmbeddedStorageRepository;
import com.medium.danieldiasjava.tomee.repository.impl.BookEmbeddedStorageRepositoryImpl;
import com.medium.danieldiasjava.tomee.service.BookStoreEmbeddedStorageService;
import com.medium.danieldiasjava.tomee.service.impl.BookStoreEmbeddedStorageServiceImpl;

public class BookApplicationMain {

public static void main(String[] args) throws InterruptedException {
		
		// Add any classes you need to an Archive
	       // or add them to a jar via any means
	       final Archive classes = Archive.archive()
	    		   .add(Banner.class)
	               .add(BookApplicationApi.class)
	               .add(BookController.class)
	               .add(Book.class)
	               .add(BookDTOMapper.class)
	               .add(BookDTO.class)
	               .add(Errors.class)
	               .add(ResponseDTO.class)
	               .add(BookStoreEvents.class)
	               .add(LoggerProduce.class)
	               .add(EmbeddedStorageManagerProducer.class)
	               .add(Service.class)
	               .add(Repository.class)
	               .add(BookStoreEmbeddedStorageService.class)
	               .add(BookStoreEmbeddedStorageServiceImpl.class)
	               .add(BookEmbeddedStorageRepository.class)
	               .add(BookEmbeddedStorageRepositoryImpl.class);
	            

	       // Place the classes where you would want
	       // them in a Tomcat install
	       final Server server = Server.builder()
	    		   .httpPort(8080)
	               // This effectively creates a webapp called ROOT
	               .add("webapps/ROOT/WEB-INF/classes", classes)
	               .add("webapps/ROOT/WEB-INF/classes/com/medium/danieldiasjava/tomee/dto/mapper/BookDTOMapperImpl.class", new File("target/classes/com/medium/danieldiasjava/tomee/dto/mapper/BookDTOMapperImpl.class"))
	               .add("webapps/ROOT/WEB-INF/classes/META-INF/beans.xml", new File("src/main/resources/META-INF/beans.xml"))
	               //.add("webapps/ROOT/WEB-INF/classes/META-INF/persistence.xml", new File("src/main/resources/META-INF/persistence.xml"))
	               .add("webapps/ROOT/WEB-INF/classes/banner.txt", new File("src/main/resources/banner.txt"))
	               .add("webapps/ROOT/WEB-INF/classes/logback.xml", new File("src/main/resources/logback.xml"))
	               .add("webapps/ROOT/WEB-INF/classes/META-INF/microprofile-config.properties", new File("src/main/resources/META-INF/microprofile-config.properties"))
	               .build();

	       System.out.println("Listening for requests at " + server.getURI());
	        new Semaphore(0).acquire();

	}


}
