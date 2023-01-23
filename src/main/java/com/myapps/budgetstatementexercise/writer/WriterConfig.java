package com.myapps.budgetstatementexercise.writer;

import com.myapps.budgetstatementexercise.domain.BudgetCategory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class WriterConfig {

    private static final  DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
    private static final DecimalFormat currencyDecimalFormat = new DecimalFormat("R$ #,###.00");
    private static final DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public WriterConfig() {
        decimalFormatSymbols.setDecimalSeparator(',');
        decimalFormatSymbols.setGroupingSeparator('.');
        currencyDecimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
    }

    @SuppressWarnings("RedundantStringFormatCall")
    @Bean
    public ItemWriter<BudgetCategory> budgetReportWriter() {
        return items -> {
            System.out.println("---- Budget report ----");
            for(BudgetCategory item: items) {
                System.out.println(String.format("[%d] %s - %s",
                    item.getExpenseCategoryCode(),
                    item.getExpenseCategoryDescription(),
                    currencyDecimalFormat.format(item.getTotalCategoryValue())
                ));
                if (!item.getBudgetItemList().isEmpty()) {
                    item.getBudgetItemList().forEach(it -> {
                        System.out.println(String.format("\t[%s] %s - %s",
                            dateTimeFormatter.format(it.getItemDate()),
                            it.getItemDescription(),
                            currencyDecimalFormat.format(it.getItemValue())
                        ));
                    });

                }
                else {
                    System.out.println("\tNo appointment found for the category.");
                }
            }
        };
    }

}
