package com.algaworks.brewer.config;

public enum AppConfigSettings {

	CHARACTER_ENCODING("UTF-8"),
	TEMPLATE_RESOLVER_PREFIX("classpath:/templates/"),
	TEMPLATE_RESOLVER_SUFFIX(".html"),
	TEMPLATE_RESOURCE_LOCATION("classpath:/static/"),
	RESOURCE_HANDLER("/**"),
	JPA_DATASOURCE("jdbc/brewerDB"),
	DATABASE_DIALECT("org.hibernate.dialect.MySQL8Dialect"),
	BIG_DECIMAL_FORMATTER("#,##0.00"),
	INTEGER_FORMATTER("#,##0"),
	CLASSFORERROR_PROCESSOR("classforerror");	
	
	private final String configuracao;

	private AppConfigSettings(String configuracao) {
		this.configuracao = configuracao;
	}
	
	public String getConfiguracao() {
		return configuracao;
	}
	
}
