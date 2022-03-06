package com.algaworks.brewer.config;

import static com.algaworks.brewer.config.AppConfigSettings.BIG_DECIMAL_FORMATTER;
import static com.algaworks.brewer.config.AppConfigSettings.CHARACTER_ENCODING;
import static com.algaworks.brewer.config.AppConfigSettings.INTEGER_FORMATTER;
import static com.algaworks.brewer.config.AppConfigSettings.RESOURCE_HANDLER;
import static com.algaworks.brewer.config.AppConfigSettings.TEMPLATE_RESOLVER_PREFIX;
import static com.algaworks.brewer.config.AppConfigSettings.TEMPLATE_RESOLVER_SUFFIX;
import static com.algaworks.brewer.config.AppConfigSettings.TEMPLATE_RESOURCE_LOCATION;

import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.algaworks.brewer.controller.CervejasController;
import com.algaworks.brewer.controller.converter.EstiloConverter;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@ComponentScan(basePackageClasses = { CervejasController.class })
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Bean
	public FormattingConversionService mvcConversionService() {
		NumberStyleFormatter bigDecimalFormatter = new NumberStyleFormatter(BIG_DECIMAL_FORMATTER.getConfiguracao());		
		NumberStyleFormatter integerFormatter = new NumberStyleFormatter(INTEGER_FORMATTER.getConfiguracao());
		
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		conversionService.addConverter(new EstiloConverter());
		conversionService.addFormatterForFieldType(BigDecimal.class, bigDecimalFormatter);		
		conversionService.addFormatterForFieldType(Integer.class, integerFormatter);		
		
		return conversionService;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(this.templateEngine());		
		resolver.setCharacterEncoding(CHARACTER_ENCODING.getConfiguracao());
		
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
		resolver.setPrefix(TEMPLATE_RESOLVER_PREFIX.getConfiguracao());
		resolver.setSuffix(TEMPLATE_RESOLVER_SUFFIX.getConfiguracao());
		resolver.setTemplateMode(TemplateMode.HTML);
		
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) { 
		registry.addResourceHandler(RESOURCE_HANDLER.getConfiguracao())
			.addResourceLocations(TEMPLATE_RESOURCE_LOCATION.getConfiguracao());
	}
	
}