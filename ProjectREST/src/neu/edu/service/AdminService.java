package neu.edu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.bean.UserCategoryBean;
import neu.edu.bean.UserProjectBean;
import neu.edu.dao.UserDAO;
import neu.edu.entity.ProjectCategory;

@Service
public class AdminService {
	@Autowired
	private UserDAO userDao;

	public List<UserCategoryBean> getFullListOfCategory() {
		List<ProjectCategory> projectCategories = userDao.getFullListOfCategories();
		List<UserCategoryBean> response = new ArrayList<>();
		if(projectCategories!=null){
		for(ProjectCategory categoryEntity: projectCategories){
			UserCategoryBean categoryBean = new UserCategoryBean();
			categoryBean.setCatDescription(categoryEntity.getCategoryDescription());
			categoryBean.setCatName(categoryEntity.getCategoryName());
			categoryBean.setUserId(categoryEntity.getUserAccount().getId());
			response.add(categoryBean);
		}
		}
		return response;
	}
	
	public boolean addCategory(UserCategoryBean userCategoryBean,Integer userId){
		return userDao.addCategory(userCategoryBean, userId);
	}

	public boolean updateCategory(UserCategoryBean userCategoryBean, Integer userId) {
		// TODO Auto-generated method stub
		return userDao.updateCategory(userCategoryBean, userId);
	}

	public boolean deleteProject(UserProjectBean userProjectBean, Integer integer) {
		// TODO Auto-generated method stub
		return userDao.deleteProject(userProjectBean,integer);
	}
	
}
