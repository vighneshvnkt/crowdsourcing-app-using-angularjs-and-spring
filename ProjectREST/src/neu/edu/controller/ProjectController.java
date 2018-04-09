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
import neu.edu.controller.input.CreatorProjectBean;
import neu.edu.service.ProjectService;

@Controller
@Path("/user/{id}/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProjectController {
	
	
	@Autowired
	private ProjectService projectService;
	
	@GET
	public Response getAllProject(@PathParam("id") String id){
		
		List<UserProjectBean> userProjectBeans =  projectService.getAllProject(new Integer(id));
		return  Response.ok().status(200).entity(userProjectBeans).build();
		
	}
	
	@POST
	@Path("/addProject")
	public Response addProject(@PathParam("id") String id, CreatorProjectBean creatorProjectBean){
		UserProjectBean userProjectBean = new UserProjectBean();
		userProjectBean.setDeadline(creatorProjectBean.getProjectDeadline());
		userProjectBean.setDesc(creatorProjectBean.getProjectDescription());
		userProjectBean.setName(creatorProjectBean.getProjectName());
		userProjectBean.setRequiredAmount(creatorProjectBean.getAmountRequired());
		UserCategoryBean userCategoryBean = new UserCategoryBean();
		userCategoryBean.setCatDescription(creatorProjectBean.getProjectCategory().getCatDescription());
		userCategoryBean.setCatName(creatorProjectBean.getProjectCategory().getCatName());
		userCategoryBean.setUserId(creatorProjectBean.getProjectCategory().getUserId());
		userProjectBean.setCategory(userCategoryBean);
		if(projectService.addProject(userProjectBean, new Integer(id)))
		{
			return Response.ok().status(200).build();
		}
		else{
			ResponseError authResponseErr = new ResponseError();
			authResponseErr.setMessage("could-not-add-project");
			
			
			
			return Response.ok().status(422).entity(authResponseErr).build();
		}

	}
	
	@POST
	@Path("/updateProject")
	public Response updateProject(@PathParam("id") String id, CreatorProjectBean creatorProjectBean){
		UserProjectBean userProjectBean = new UserProjectBean();
		userProjectBean.setDeadline(creatorProjectBean.getProjectDeadline());
		userProjectBean.setDesc(creatorProjectBean.getProjectDescription());
		userProjectBean.setName(creatorProjectBean.getProjectName());
		userProjectBean.setRequiredAmount(creatorProjectBean.getAmountRequired());
		UserCategoryBean userCategoryBean = new UserCategoryBean();
		userCategoryBean.setCatDescription(creatorProjectBean.getProjectCategory().getCatDescription());
		userCategoryBean.setCatName(creatorProjectBean.getProjectCategory().getCatName());
		userCategoryBean.setUserId(creatorProjectBean.getProjectCategory().getUserId());
		userProjectBean.setCategory(userCategoryBean);
		if(projectService.updateProject(userProjectBean, new Integer(id)))
		{
			return Response.ok().status(200).build();
		}
		else{
			ResponseError authResponseErr = new ResponseError();
			authResponseErr.setMessage("could-not-add-project");
			
			
			
			return Response.ok().status(422).entity(authResponseErr).build();
		}

	}

}
