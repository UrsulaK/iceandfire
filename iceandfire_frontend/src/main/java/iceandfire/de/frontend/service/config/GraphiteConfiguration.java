package iceandfire.de.frontend.service.config;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

@Configuration
public class GraphiteConfiguration {

	@Autowired
    private MetricRegistry registry;

    @PostConstruct
    public void initialize() {
        final Graphite graphite = new Graphite(new InetSocketAddress("0.0.0.0", 2003));
        final GraphiteReporter reporter = GraphiteReporter.forRegistry(registry)
                .prefixedWith("iceandfire.frontend")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);
        reporter.start(1, TimeUnit.MINUTES);

    }
}
