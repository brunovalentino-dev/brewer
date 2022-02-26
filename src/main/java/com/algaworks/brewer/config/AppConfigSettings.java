package com.algaworks.brewer.config;

public enum AppConfigSettings {

	CHARACTER_ENCODING("UTF-8"),
	TEMPLATE_RESOLVER_PREFIX("classpath:/templates/"),
	TEMPLATE_RESOLVER_SUFFIX(".html"),
	TEMPLATE_RESOURCE_LOCATION("classpath:/static/"),
	RESOURCE_HANDLER("/**");
	
	private final String configuracao;

	private AppConfigSettings(String configuracao) {
		this.configuracao = configuracao;
	}
	
	public String getConfiguracao() {
		return configuracao;
	}
	
}
