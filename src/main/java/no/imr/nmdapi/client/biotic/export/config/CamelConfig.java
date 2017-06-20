package no.imr.nmdapi.client.biotic.export.config;

import java.util.ArrayList;
import java.util.List;
import no.imr.nmdapi.client.biotic.route.GenerateAll;
import no.imr.nmdapi.client.biotic.route.GenerateUpdated;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author Terry Hannant <a5119>
 */
@Configuration
public class CamelConfig extends CamelConfiguration implements InitializingBean {

    @Autowired
    private GenerateAll generateAllRoute;

    @Autowired
    public GenerateUpdated generateUpdatedRoute;

    @Override
    public List<RouteBuilder> routes() {
        List<RouteBuilder> routes = new ArrayList<>();
        routes.add(generateAllRoute);
        routes.add(generateUpdatedRoute);
        return routes;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // no properties loaded so not used
    }

}
