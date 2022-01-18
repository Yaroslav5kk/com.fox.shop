package com.fox.shop.client.bot.config;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

@Component
public class CommandProcessorConfiguration implements ApplicationListener<ContextRefreshedEvent> {

  @Value("${base-package}")
  private String basePackage;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
  /*  final ClassPathScanningCandidateComponentProvider scanner =
            new ClassPathScanningCandidateComponentProvider(false);
    scanner.addIncludeFilter(new AnnotationTypeFilter(CommandProcessorComponent.class));
    scanner.findCandidateComponents(basePackage);

    ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
    applicationContext.getParentBeanFactory().
    applicationContext.getBeansWithAnnotation(CommandProcessorComponent.class)
            .forEach((name, object) -> );

    contextRefreshedEvent.getApplicationContext().getParent().getAutowireCapableBeanFactory().initializeBean();
  */}
}

































