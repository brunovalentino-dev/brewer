package com.algaworks.brewer.thymeleaf.processor;

import static com.algaworks.brewer.config.AppConfigSettings.CLASSFORERROR_PROCESSOR;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.util.FieldUtils;
import org.thymeleaf.templatemode.TemplateMode;

public class ClassForErrorAttributeTagProcessor extends AbstractAttributeTagProcessor {

	private static final int PRECEDENCE = 1000;
	
	public ClassForErrorAttributeTagProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, CLASSFORERROR_PROCESSOR.getConfiguracao(), true, PRECEDENCE, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		boolean erroNoCampo = FieldUtils.hasErrors(context, attributeValue);
		
		if (erroNoCampo) {
			String classeTag = tag.getAttributeValue("class");			
			structureHandler.setAttribute("class", classeTag + " has-error");
		}
	}

}
