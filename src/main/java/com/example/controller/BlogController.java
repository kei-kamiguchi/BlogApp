package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.entity.Blog;
import com.example.form.BlogForm;
import com.example.repository.BlogCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("blog")
public class BlogController {
	@Autowired
	BlogCrudRepository repository;
	
	@GetMapping("index")
	public String index(Model model) {
		model.addAttribute("blogs", repository.findAll());
		return "index";
	}
	@GetMapping("new")
    public String addBlog() {  
        return "new";
    }
//	When the Form class is not used
//    @PostMapping("create") 
//    public String create(Blog blog, @RequestParam("title") String title, @RequestParam("content") String content) { //2
//		blog.setTitle(title);
//		blog.setContent(content);
//		repository.save(blog);
//        return "redirect:index";
//    }
    @PostMapping("create") 
    public String create(BlogForm blogForm, Blog blog) {
		blog.setTitle(blogForm.getTitle());
		blog.setContent(blogForm.getContent());
		repository.save(blog);
        return "redirect:index";
    }
	@GetMapping("show/{id}")
    public String show(Model model, @PathVariable Integer id) {
		model.addAttribute("blog", repository.findById(id).get());
        return "show";
    }
	@GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
		model.addAttribute("blog", repository.findById(id).get());
        return "edit";
    }
    @PostMapping("update/{id}") 
    public String update(BlogForm blogForm, @PathVariable Integer id) {
    	Blog blog = repository.findById(id).get();
		blog.setTitle(blogForm.getTitle());
		blog.setContent(blogForm.getContent());
		repository.save(blog);
        return "redirect:/blog/show/{id}";
    }
    @PostMapping("delete") 
    public String delete(@RequestParam Integer id, Model model) {
		repository.deleteById(id);
        return "redirect:/blog/index";
    }
}