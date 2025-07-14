package sn.esp.service_web.soap.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter 
{

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) 
    {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "utilisateurWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema utilisateurSchema) 
    {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("UtilisateurPort");
        definition.setLocationUri("/ws");
        definition.setTargetNamespace("http://sn.esp.service_web/gen");
        definition.setSchema(utilisateurSchema);
        return definition;
    }

    @Bean
    public XsdSchema utilisateurSchema() 
    {
        return new SimpleXsdSchema(new FileSystemResource("src/main/xsd/utilisateur.xsd"));
    }
}
