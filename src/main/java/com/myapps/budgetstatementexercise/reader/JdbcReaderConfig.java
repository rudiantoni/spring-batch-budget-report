package com.myapps.budgetstatementexercise.reader;

import com.myapps.budgetstatementexercise.domain.BudgetItem;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JdbcReaderConfig {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final DataSource dataSource;

    public JdbcReaderConfig(
        @Qualifier("batchAppDataSource") DataSource dataSource
    ) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcPagingItemReader<BudgetItem> bugdetStatementJdbcPagingReader() throws Exception {
        return new JdbcPagingItemReaderBuilder<BudgetItem>()
            .name("bugdetStatementJdbcPagingReader")
            .dataSource(dataSource)
            .queryProvider(pgQueryProvider())
            .pageSize(1)
            .rowMapper(rowMapper())
            .build();

    }

    private PostgresPagingQueryProvider pgQueryProvider() throws Exception {
        Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);
        sortKeys.put("expenditure_nature_code", Order.ASCENDING);

        PostgresPagingQueryProvider queryProvider = new PostgresPagingQueryProvider();
        queryProvider.setSelectClause("select *");
        queryProvider.setFromClause("from public.appointment");
        queryProvider.setSortKeys(sortKeys);
        queryProvider.init(dataSource);

        return queryProvider;
    }

    private RowMapper<BudgetItem> rowMapper() {

        return new RowMapper<BudgetItem>() {
            @Override
            public BudgetItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                BudgetItem budgetItem = new BudgetItem();
                budgetItem.setExpenseCategoryCode(rs.getLong("expenditure_nature_code"));
                budgetItem.setExpenseCategoryDescription(rs.getString("expenditure_nature_description"));
                budgetItem.setItemDescription(rs.getString("appointment_description"));
                budgetItem.setItemDate(LocalDate.parse(rs.getString("appointment_date"), dateTimeFormatter));
                budgetItem.setItemValue(rs.getFloat("appointment_value"));
                return budgetItem;
            }

        };

    }

}
