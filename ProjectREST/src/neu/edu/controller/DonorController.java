package neu.edu.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.bean.UserCommentsBean;
import neu.edu.bean.UserDonationBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.controller.error.ResponseError;
import neu.edu.controller.input.DonorCommentBean;
import neu.edu.controller.input.DonorDonationBean;
import neu.edu.entity.UserComments;
import neu.edu.service.DonorService;


@Controller
@Path("/donor/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DonorController {
	@Autowired
	private DonorService donorService;

	@POST
	@Path("/addDonation")
	public Response addDonation(@PathParam("id") String id, DonorDonationBean donorDonationBean){
		
		UserDonationBean userDonationBean = new UserDonationBean();
		userDonationBean.getProject().setName(donorDonationBean.getProjectName());
		userDonationBean.setDonation_amount(donorDonationBean.getDonationAmount());
		userDonationBean.getCardInfo().setCardNumber(donorDonationBean.getCardNumber());
		userDonationBean.getCardInfo().setCvv(donorDonationBean.getCvv());
		userDonationBean.getCardInfo().setExpiryDate(donorDonationBean.getExpiryDate());
		if(donorService.addDonation(userDonationBean, new Integer(id)))
		{
			return Response.ok().status(200).build();
		}
		else{
			ResponseError authResponseErr = new ResponseError();
			authResponseErr.setMessage("incorrect-card-details");
			
			
			
			return Response.ok().status(422).entity(authResponseErr).build();
		}
	
	}
	
	@POST
	@Path("/addComment")
	public Response addComment(@PathParam("id") String id, DonorCommentBean donorCommentBean){
		
		UserCommentsBean userCommentsBean = new UserCommentsBean();
		userCommentsBean.setComment(donorCommentBean.getCommentName());
		userCommentsBean.setComment_date(donorCommentBean.getCommentDate());
		userCommentsBean.setProjectName(donorCommentBean.getProjectName());
		if(donorService.addComment(userCommentsBean, new Integer(id)))
		{
			return Response.ok().status(200).build();
		}
		else{
			ResponseError authResponseErr = new ResponseError();
			authResponseErr.setMessage("incorrect-card-details");
			
			
			
			return Response.ok().status(422).entity(authResponseErr).build();
		}
	
	}
	
	@POST
	@Path("/updateDonation")
	public Response updateDonation(@PathParam("id") String id, DonorDonationBean donorDonationBean){
		UserDonationBean userDonationBean = new UserDonationBean();
		userDonationBean.getProject().setName(donorDonationBean.getProjectName());
		userDonationBean.setDonation_amount(donorDonationBean.getDonationAmount());
		userDonationBean.getCardInfo().setCardNumber(donorDonationBean.getCardNumber());
		userDonationBean.getCardInfo().setCvv(donorDonationBean.getCvv());
		userDonationBean.getCardInfo().setExpiryDate(donorDonationBean.getExpiryDate());
		if(donorService.updateDonation(userDonationBean, new Integer(id)))
		{
			return Response.ok().status(200).build();
		}
		else{
			ResponseError authResponseErr = new ResponseError();
			authResponseErr.setMessage("card-details-incorrect");
			
			
			
			return Response.ok().status(422).entity(authResponseErr).build();
		}
	
	}
	
	
	
}
