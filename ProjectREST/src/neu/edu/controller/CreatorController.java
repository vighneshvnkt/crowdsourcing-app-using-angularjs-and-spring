package neu.edu.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.bean.UserCategoryBean;
import neu.edu.service.CreatorService;


@Controller
@Path("/creator/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreatorController {

	@Autowired
	private CreatorService creatorService;
	
	@GET
	public Response getAllCategory(@PathParam("id") String id){
		
		List<UserCategoryBean> userCategoryBeans =  creatorService.getFullListOfCategory();
		return  Response.ok().status(200).entity(userCategoryBeans).build();
		
	}
}
