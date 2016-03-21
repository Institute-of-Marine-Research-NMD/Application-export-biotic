package no.imr.nmdapi.client.biotic.route;

import no.imr.messaging.processor.ExceptionProcessor;
import no.imr.nmdapi.exceptions.CantWriteFileException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.util.UnsafeUriCharactersEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Terry Hannant <a5119>
 */
public class GenerateAll extends RouteBuilder {

    
    @Autowired
    @Qualifier("configuration")
    private org.apache.commons.configuration.Configuration configuration;

    @Override
    public void configure() {

        onException(CantWriteFileException.class).continued(true).
                process(new ExceptionProcessor(configuration.getString("application.name"))).
                to("jms:queue:".concat(configuration.getString("queue.outgoing.criticalFailure")));

            from("jms:queue:".concat(configuration.getString("queue.incoming.bioticRefresh")))
                    .choice()
                       .when(simple("${in.header.imr:refresh} == 'all' ")).to("getAllBioticMissions")
                    //Example future addition    .when(header("imr:refresh").startsWith("id")).to("parse")
                        .otherwise().stop()
                    .end()
                    .split(body())
                    .to("bioticLoaderService")
                    .multicast()
                     .to("jms:queue:".concat(configuration.getString("queue.outgoing.dataset")),
                            "jms:queue:".concat(configuration.getString("queue.outgoing.success"))
                );
    }
    
}
