package com.lcm.jnode.controller;

import java.text.SimpleDateFormat;

import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.Controller;
import com.lcm.jnode.interceptor.AdminInterceptor;
import com.lcm.jnode.interceptor.SidebarInterceptor;
import com.lcm.jnode.model.Blog;

@Before(AdminInterceptor.class)
public class BlogController extends Controller{
    
    @ClearInterceptor(ClearLayer.UPPER)
    @Before(SidebarInterceptor.class)
	public void index() {
	    Blog blog = Blog.dao.findFallById(getParaToInt(0, 1));
	    blog.set("view_count", blog.getInt("view_count") + 1).update();
	    blog.set("update_time", new SimpleDateFormat("yyyy年 MM月 dd日").format(blog.getTimestamp("update_time")));
	    setAttr("blog", blog);
	    setAttr("title", blog.getStr("title"));
	    render("blog");
	}

	public void add() {
	    render("admin/blog-add");
	}

	public void save() {
		getModel(Blog.class).save();
		forwardAction("admin/blog");
	}

	public void edit() {
		setAttr("Blog", Blog.dao.findById(getParaToInt()));
	}

	public void update() {
		getModel(Blog.class).update();
		forwardAction("/blog");
	}

	public void delete() {
		Blog.dao.deleteById(getParaToInt());
		forwardAction("/blog");
	}
}
