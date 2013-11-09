package com.sjsu.movie.service.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sjsu.movie.bean.MovieUserBean;
import com.sjsu.movie.domain.MovieUser;
import com.sjsu.movie.service.MovieService;
import com.sjsu.movie.service.MovieUserService;
@WebService(name="MovieUserService")
public class MovieUserEndPoint extends SpringBeanAutowiringSupport {
	
	@Autowired
	MovieUserService movieUserService;
	
	public long countAllMovieUsers(){
		return movieUserService.countAllMovieUsers();
		
	}


	public void deleteMovieUser(Long userId){
		MovieUser user = movieUserService.findMovieUser(userId);
		
		//TODO: soft delete user
		movieUserService.deleteMovieUser(user);
	}


	public MovieUserBean findMovieUser(Long id){
		MovieUser user = movieUserService.findMovieUser(id);
		if (user != null){
			return user.getBean();
		}
		return null;
		
	}


	public List<MovieUserBean> findAllMovieUsers(){
		List<MovieUser> userList = movieUserService.findAllMovieUsers();
		List<MovieUserBean> beanList = new ArrayList<MovieUserBean>();
		if (CollectionUtils.isNotEmpty(userList)){
			for(MovieUser user: userList){
				beanList.add(user.getBean());
			}
		}
		return beanList;
	}


	public List<MovieUserBean> findMovieUserEntries(int firstResult, int maxResults){
		List<MovieUser> userList = movieUserService.findMovieUserEntries(firstResult, maxResults);
		List<MovieUserBean> beanList = new ArrayList<MovieUserBean>();
		if (CollectionUtils.isNotEmpty(userList)){
			for(MovieUser user: userList){
				beanList.add(user.getBean());
			}
		}
		return beanList;
	}


	public void saveMovieUser(MovieUserBean bean){
		MovieUser user = new MovieUser().copyData(bean);
		movieUserService.saveMovieUser(user);
	}


	public MovieUser updateMovieUser(MovieUserBean bean){
		MovieUser user = movieUserService.findMovieUser(bean.getId()).copyData(bean);
		movieUserService.saveMovieUser(user);	
		return user;
	}

}
