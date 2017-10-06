package net.coatli.java;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VelocityJavaCodeApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(VelocityJavaCodeApplication.class);

  public static void main(final String[] args) {

    final StringWriter sw = new StringWriter();

    LOGGER.info("Generating JavaBeans for Model with Velocity");

    final VelocityEngine ve = new VelocityEngine();

    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

    final VelocityContext context = new VelocityContext();
    context.put("package", "net.coatli.java");
    context.put("class", getJavaBeanDefinition());

    ve.getTemplate("javabean.vm").merge(context, sw);

    LOGGER.info("Output: {}", sw);

  }

  private static Map<String, Object> getJavaBeanDefinition() {

    final Map<String, Object> classDefinition = new HashMap<>();
    final Map<String, Object> propertiesDefinition = new HashMap<>();
    final Map<String, Object> keyDefinition = new HashMap<>();
    final Map<String, Object> nameDefinition = new HashMap<>();
    final Map<String, Object> ageDefinition = new HashMap<>();

    keyDefinition.put("name", "key");
    keyDefinition.put("type", "String");

    nameDefinition.put("name", "name");
    nameDefinition.put("type", "String");

    ageDefinition.put("name", "age");
    ageDefinition.put("type", "Integer");

    propertiesDefinition.put("key", keyDefinition);
    propertiesDefinition.put("name", nameDefinition);
    propertiesDefinition.put("age", ageDefinition);

    classDefinition.put("name", "Client");
    classDefinition.put("properties", propertiesDefinition);

    return classDefinition;

  }
}
