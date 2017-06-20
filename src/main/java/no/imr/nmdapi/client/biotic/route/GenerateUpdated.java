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
public class GenerateUpdated extends RouteBuilder {

    @Autowired
    @Qualifier("configuration")
    private org.apache.commons.configuration.Configuration configuration;

    @Override
    public void configure() {

        onException(CantWriteFileException.class).continued(true).
                process(new ExceptionProcessor(configuration.getString("application.name"))).
                to("jms:queue:".concat(configuration.getString("queue.outgoing.criticalFailure")));

        from("quartz://cacheRefresh?cron=" + UnsafeUriCharactersEncoder.encode(configuration.getString("cron.activation.time")))
                .from("timer://runOnce?repeatCount=1&delay=5000")
                .to("getAllUpdatedBioticMissions")
                .split(body())
                .to("bioticLoaderService")
                .multicast()
                .to("jms:queue:".concat(configuration.getString("queue.outgoing.dataset")),
                        "jms:queue:".concat(configuration.getString("queue.outgoing.success"))
                );
    }

}
