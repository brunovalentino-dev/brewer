package com.algaworks.brewer.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.algaworks.brewer.controller.CervejasController;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@ComponentScan(basePackageClasses = { CervejasController.class })
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private static final String CHARACTER_ENCODING = "UTF-8";
	private static final String TEMPLATE_RESOLVER_PREFIX = "classpath:/templates/";
	private static final String TEMPLATE_RESOLVER_SUFFIX = ".html";
	private static final String TEMPLATE_RESOURCE_LOCATION = "classpath:/static/";
	
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(this.templateEngine());
		resolver.setCharacterEncoding(CHARACTER_ENCODING);
		
		return resolver;
	}

	@Bean
	public TemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(this.templateResolver());
		engine.addDialect(new LayoutDialect());
		
		return engine;
	}

	private ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix(TEMPLATE_RESOLVER_PREFIX);
		resolver.setSuffix(TEMPLATE_RESOLVER_SUFFIX);
		resolver.setTemplateMode(TemplateMode.HTML);
		
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { 
		registry.addResourceHandler("/**").addResourceLocations(TEMPLATE_RESOURCE_LOCATION);
	}
	
}