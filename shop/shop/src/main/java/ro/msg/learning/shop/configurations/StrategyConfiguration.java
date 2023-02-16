package ro.msg.learning.shop.configurations;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.services.LocationService;
import ro.msg.learning.shop.services.StockService;
import ro.msg.learning.shop.services.interfaces.IStrategy;
import ro.msg.learning.shop.services.strategies.MostAbundantStrategy;
import ro.msg.learning.shop.services.strategies.SingleLocationStrategy;
import ro.msg.learning.shop.utils.Strategy;

@Configuration
@Data
@RequiredArgsConstructor
public class StrategyConfiguration {

    @Value("${app.strategy}")
    private String strategy;

    private final StockService stockService;
    private final LocationService locationService;

    @Bean
    public IStrategy findStrategy() {
        if (strategy.equals(Strategy.SINGLE_LOCATION.toString())) {
            return new SingleLocationStrategy(locationService, stockService);
        }
        else return new MostAbundantStrategy(stockService);

    }
}
