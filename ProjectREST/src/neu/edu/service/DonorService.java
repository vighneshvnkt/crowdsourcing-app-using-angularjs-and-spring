package neu.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.bean.UserCommentsBean;
import neu.edu.bean.UserDonationBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.dao.UserDAO;

@Service
public class DonorService {

	@Autowired
	private UserDAO userDao;
	
	public boolean addDonation(UserDonationBean userDonationBean,Integer userId){
		return userDao.addDonation(userDonationBean, userId);
	}

	public boolean updateDonation(UserDonationBean userDonationBean, Integer userId) {
		// TODO Auto-generated method stub
		return userDao.updateDonation(userDonationBean, userId);
	}

	public boolean addComment(UserCommentsBean userCommentBean, Integer userId) {
		return userDao.addComment(userCommentBean, userId);
	}
}
