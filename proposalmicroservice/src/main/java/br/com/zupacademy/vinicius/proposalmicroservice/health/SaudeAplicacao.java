package br.com.zupacademy.vinicius.proposalmicroservice.health;

import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

@WebEndpoint(id="health")
public class SaudeAplicacao implements HealthIndicator {
    
    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }
    
    @Override
    public Health health() {
        return null;
    }
}
