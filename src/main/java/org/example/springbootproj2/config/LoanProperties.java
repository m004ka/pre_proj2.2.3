package org.example.springbootproj2.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "loan")
public class LoanProperties {

    private int minIncome;

    private int minPriceCar;

    private int loanByCarPercent;

    private int loanByIncomePeriod;
}
