package com.myapps.budgetstatementexercise.step;

import com.myapps.budgetstatementexercise.domain.BudgetItem;
import com.myapps.budgetstatementexercise.reader.BudgetItemJdbcDelegatedReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public StepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Value("${source.data.use.file}")
    private Boolean useFile;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public Step budgetReportStep(
        MultiResourceItemReader<BudgetItem> budgetReportReader,
        JdbcPagingItemReader bugdetStatementJdbcPagingReader,
        ItemWriter budgetReportWriter
    ) {

        if (useFile) {
            return stepBuilderFactory
                .get("budgetStatementStep")
                .chunk(1)
                .reader(budgetReportReader)
                .writer(budgetReportWriter)
                .build();
        } else {
            return stepBuilderFactory
                .get("budgetStatementStep")
                .chunk(1)
                .reader(new BudgetItemJdbcDelegatedReader(bugdetStatementJdbcPagingReader))
                .writer(budgetReportWriter)
                .build();
        }
    }

}
