package com.localstore.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.localstore.dao.CategoryDao;
import com.localstore.dao.UserDao;
import com.localstore.modal.Category;
import com.localstore.modal.User;
import com.localstore.service.CategoryService;

@Transactional
@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Category addCategoryToUser(Category category, long idUser) {
		User user = userDao.findById(idUser).orElse(null);
		user.addCategoryToUser(category);
		return categoryDao.save(category);
	}

	@Override
	public Category editCategory(Category category, long id) {
		Category existsCategory = categoryDao.findById(id).orElse(null);
		existsCategory.setName(category.getName());
		return categoryDao.save(existsCategory);
	}

	@Override
	public Category findCategoryById(long id) {
		return categoryDao.findById(id).orElse(null);
	}

	@Override
	public void deleteCategory(long id) {
		categoryDao.deleteById(id);
	}

	@Override
	public List<Category> findAllCategories() {
		return categoryDao.findAll();
	}

	@Override
	public List<Category> findCategoriesForUser(long id) {
		User user = userDao.findById(id).orElse(null);
		return user.getCategories();
	}

}
