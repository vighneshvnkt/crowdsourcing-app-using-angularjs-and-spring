package neu.edu.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.bean.UserRegistrationBean;
import neu.edu.dao.UserDAO;
import neu.edu.entity.UserAccount;

@Service
public class RegisterService {

	@Autowired
	private UserDAO userDao;
	
	
	public Integer checkUser(String username){
		System.out.println("Register Service is called Called");
		UserAccount user = userDao.checkUser(username);
		
		if (user == null) {
			System.out.println("User Not Found");
			return null;
		} else {
			System.out.println("User  Found");
			return user.getId();
		}
	}

	public Integer createUser(UserRegistrationBean userRegistrationBean) {

		Integer userAccountID = checkUser(userRegistrationBean.getUsername());
		if(userAccountID == null){
		UserAccount userAccount = new UserAccount();
		userAccount.setEmail(userRegistrationBean.getEmail());
		userAccount.setFirstName(userRegistrationBean.getFirstName());
		userAccount.setLastName(userRegistrationBean.getLastName());
		userAccount.setMobile(userRegistrationBean.getMobile());
		userAccount.setTitle(userRegistrationBean.getTitle());
		userAccount.setUserType(userRegistrationBean.getType());
		userAccount.setUserName(userRegistrationBean.getUsername());
		userAccount.setPassword(userRegistrationBean.getPassword());
		// 2017-12-31
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (userRegistrationBean.getDob() != null) {
			try {
				userAccount.setDob(sdf.parse(userRegistrationBean.getDob()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		userAccount = userDao.createUser(userAccount);

		return userAccount.getId();
		}
		else
			return null;
	}

}
