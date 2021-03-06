package iceandfire.de.service.config;

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
        final Graphite graphite = new Graphite(new InetSocketAddress("grafana", 2003)); // running in docker
        //final Graphite graphite = new Graphite(new InetSocketAddress("192.168.99.100", 2003)); //running locally
        final GraphiteReporter reporter = GraphiteReporter.forRegistry(registry)
                .prefixedWith("iceandfire.service")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite);
        reporter.start(1, TimeUnit.MINUTES);

    }
}
