package com.medium.danieldiasjava.tomee.controller;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.medium.danieldiasjava.tomee.dto.BookDTO;
import com.medium.danieldiasjava.tomee.dto.ResponseDTO;
import com.medium.danieldiasjava.tomee.service.BookStoreEmbeddedStorageService;



@Path("books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BookController {

	@Inject
	private BookStoreEmbeddedStorageService bookStoreEmbeddedStorageService;
	
	@GET
	@Path("/v2")
	public Response getBooksEmbeddedStorage() {
		
		var books = bookStoreEmbeddedStorageService.getBooks();
		if(books.isEmpty()) {
			return Response.status(Status.OK).entity(new ResponseDTO(books,Status.NO_CONTENT.getStatusCode())).build();	
		}
		return Response.status(Status.OK).entity(new ResponseDTO(books,Status.OK.getStatusCode())).build();
	}

	@POST
	@Path("/v2")
	public Response saveBooksEmbeddedStorage(@Valid BookDTO bookDto) {
		this.bookStoreEmbeddedStorageService.saveBook(bookDto);
		return Response.status(Status.CREATED).entity(new ResponseDTO(bookDto,"saved!",Status.CREATED.getStatusCode())).build();
	}
}
