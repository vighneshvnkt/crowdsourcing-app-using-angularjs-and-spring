package neu.edu.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.bean.UserRegistrationBean;

import neu.edu.controller.error.ResponseError;
import neu.edu.controller.input.UserRegisterBean;
import neu.edu.service.RegisterService;


@Controller
@Path("/registration")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class RegistrationController {
	
	@Autowired
	private RegisterService registerService;
	
	
	@POST
	public Response registerUser(UserRegisterBean registerBean) {

		UserRegistrationBean userRegistrationBean = new UserRegistrationBean();
		userRegistrationBean.setFirstName(registerBean.getFirstName());
		userRegistrationBean.setLastName(registerBean.getLastName());
		userRegistrationBean.setTitle(registerBean.getTitle());
		userRegistrationBean.setUsername(registerBean.getUsername());
		userRegistrationBean.setPassword(registerBean.getPassword().hashCode()+"");
		userRegistrationBean.setEmail(registerBean.getEmail());
		userRegistrationBean.setMobile(registerBean.getMobile());
		userRegistrationBean.setDob(registerBean.getDob());
		userRegistrationBean.setType(registerBean.getType());
		
		if(registerService.createUser(userRegistrationBean)!= null){
			return Response.ok().status(200).build();
		}
		else{
			ResponseError authResponseErr = new ResponseError();
			authResponseErr.setMessage("user-already-exists");
			return Response.ok().status(422).entity(authResponseErr).build();
		}	

	}

}
