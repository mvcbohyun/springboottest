package com.bhjang.configuration;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SitemeshConfiguration extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/community", "/WEB-INF/views/decorator/Default-layout.jsp");
		builder.addDecoratorPath("/notice", "/WEB-INF/views/decorator/Default-layout.jsp");
		builder.addDecoratorPath("/faq", "/WEB-INF/views/decorator/Default-layout.jsp");
		builder.addDecoratorPath("/inquity", "/WEB-INF/views/decorator/Default-layout.jsp");
		super.applyCustomConfiguration(builder);
	}
}
