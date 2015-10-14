package no.imr.nmdapi.client.biotic.export.config;

import no.imr.messaging.processor.ExceptionProcessor;
import no.imr.nmdapi.exceptions.CantWriteFileException;
import no.imr.nmdapi.exceptions.S2DException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.routepolicy.quartz.CronScheduledRoutePolicy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author Terry Hannant <a5119>
 */
@Configuration
public class CamelConfig extends  SingleRouteCamelConfiguration  implements InitializingBean {
    
    @Autowired
    @Qualifier("configuration")
    private  org.apache.commons.configuration.Configuration configuration;
    
    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public RouteBuilder route() {
        return new RouteBuilder() {

            @Override
            public void configure() {
                
             CronScheduledRoutePolicy startPolicy = new CronScheduledRoutePolicy();
                 startPolicy.setRouteStartTime(configuration.getString("cron.activation.time"));

                onException(CantWriteFileException.class).continued(true).
                        process(new ExceptionProcessor(configuration.getString("application.name"))).
                        to("jms:queue:".concat(configuration.getString("queue.outgoing.criticalFailure")));
                
                from("timer://runOnce?repeatCount=1&delay=5000").routePolicy(startPolicy) 
                        .to("getAllUpdatedBioticMissions")
                        .split(body())
                        .to("bioticLoaderService")
                        .multicast()
                        .to("jms:queue:".concat(configuration.getString("queue.outgoing.dataset")),
                             "jms:queue:".concat(configuration.getString("queue.outgoing.success"))
                        );
            }
        };
    }

    
    
    
}
