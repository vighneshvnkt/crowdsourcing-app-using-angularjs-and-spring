package neu.edu.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.bean.UserCategoryBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.controller.error.ResponseError;
import neu.edu.controller.input.AdminCategoryBean;
import neu.edu.controller.input.CreatorProjectBean;
import neu.edu.controller.input.DeleteProjectBean;
import neu.edu.service.AdminService;


@Controller
@Path("/admin/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GET
	public Response getAllCategory(@PathParam("id") String id){
		
		List<UserCategoryBean> userCategoryBeans =  adminService.getFullListOfCategory();
		return  Response.ok().status(200).entity(userCategoryBeans).build();
		
	}
	
	@POST
	@Path("/addCategory")
	public Response addCategory(@PathParam("id") String id, AdminCategoryBean adminCategoryBean){
		UserCategoryBean userCategoryBean = new UserCategoryBean();
		userCategoryBean.setCatDescription(adminCategoryBean.getCatDescription());
		userCategoryBean.setCatName(adminCategoryBean.getCatName());
		userCategoryBean.setUserId(new Integer(id));
		
		if(adminService.addCategory(userCategoryBean, new Integer(id)))
		{
			return Response.ok().status(200).build();
		}
		else{
			ResponseError authResponseErr = new ResponseError();
			authResponseErr.setMessage("could-not-add-project-category");	
			return Response.ok().status(422).entity(authResponseErr).build();
		}
	}
	
	@POST
	@Path("/updateCategory")
	public Response updateCategory(@PathParam("id") String id, AdminCategoryBean adminCategoryBean){
		UserCategoryBean userCategoryBean = new UserCategoryBean();
		userCategoryBean.setCatDescription(adminCategoryBean.getCatDescription());
		userCategoryBean.setCatName(adminCategoryBean.getCatName());
		
		if(adminService.updateCategory(userCategoryBean, new Integer(id)))
		{
			return Response.ok().status(200).build();
		}
		else{
			ResponseError authResponseErr = new ResponseError();
			authResponseErr.setMessage("could-not-add-project-category");	
			return Response.ok().status(422).entity(authResponseErr).build();
		}
	}
	

	@POST
	@Path("/deleteProject")
	public Response deleteProject(@PathParam("id") String id, DeleteProjectBean deleteProjectBean){

		UserProjectBean userProjectBean = new UserProjectBean();
		userProjectBean.setName(deleteProjectBean.getProjectName());
		
		if(adminService.deleteProject(userProjectBean, new Integer(id)))
		{
			return Response.ok().status(200).build();
		}
		else{
			ResponseError authResponseErr = new ResponseError();
			authResponseErr.setMessage("could-not-add-project-category");	
			return Response.ok().status(422).entity(authResponseErr).build();
		}
	}

}
