package com.myapps.budgetstatementexercise.reader;

import com.myapps.budgetstatementexercise.domain.BudgetItem;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("NullableProblems")
@Configuration
public class ReaderConfig {

    @Value("${source.data.files.path}")
    private String sourceDataFilesPath;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public MultiResourceItemReader budgetReportReader(
        FlatFileItemReader delimitedFileReaderDel
    ) throws IOException {
        GenericApplicationContext genericApplicationContext = new GenericApplicationContext();
        Resource[] resources = genericApplicationContext.getResources("file:" + sourceDataFilesPath);
        return new MultiResourceItemReaderBuilder<>()
            .name("budgetReportReader")
            .resources(resources)
            .delegate(new BudgetItemDelegatedReader(delimitedFileReaderDel))
            .build();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public FlatFileItemReader delimitedFileReaderDel() throws IOException {
        return new FlatFileItemReaderBuilder()
            .name("delimitedFileReader")
            .lineMapper(lineMapper())
            .build();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private LineMapper lineMapper() {
        DefaultLineMapper lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(lineTokenizers());
        lineMapper.setFieldSetMapper(fieldSetMapper());
        return lineMapper;
    }

    private LineTokenizer lineTokenizers() {
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("expenseCategoryCode", "expenseCategoryDescription", "itemDescription", "itemDate", "itemValue");
        lineTokenizer.setIncludedFields(0, 1, 2, 3, 4);
        return lineTokenizer;
    }

    @SuppressWarnings({"rawtypes", "SameParameterValue"})
    private FieldSetMapper fieldSetMapper() {
        return new FieldSetMapper<BudgetItem>() {
            @Override
            public BudgetItem mapFieldSet(FieldSet fieldSet) throws BindException {
                BudgetItem budgetItem = new BudgetItem();
                budgetItem.setExpenseCategoryCode(fieldSet.readLong("expenseCategoryCode"));
                budgetItem.setExpenseCategoryDescription(fieldSet.readString("expenseCategoryDescription"));
                budgetItem.setItemDescription(fieldSet.readString("itemDescription"));
                String itemDateStr = fieldSet.readString("itemDate");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate itemDate = LocalDate.parse(itemDateStr, formatter);
                budgetItem.setItemDate(itemDate);
                budgetItem.setItemValue(fieldSet.readFloat("itemValue"));
                return budgetItem;
            }
        };

    }


}
