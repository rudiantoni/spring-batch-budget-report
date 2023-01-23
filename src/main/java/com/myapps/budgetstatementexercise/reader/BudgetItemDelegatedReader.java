package com.myapps.budgetstatementexercise.reader;

import com.myapps.budgetstatementexercise.domain.BudgetCategory;
import com.myapps.budgetstatementexercise.domain.BudgetItem;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

public class BudgetItemDelegatedReader implements ItemStreamReader<BudgetCategory>, ResourceAwareItemReaderItemStream<BudgetCategory> {

    // This is the final reader that will be in charge to read the files from the data source
    private final FlatFileItemReader<Object> delegate;

    private BudgetItem lastBudgetItem;

    public BudgetItemDelegatedReader(FlatFileItemReader<Object> delegate) {
        this.delegate = delegate;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void setResource(Resource resource) {
        delegate.setResource(resource);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

    @Override
    public BudgetCategory read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        // If the last one hasn't been defined yet
        if (lastBudgetItem == null) {
            lastBudgetItem = (BudgetItem) delegate.read();
        }

        // If there's still data to read or not (EOF, no rows, etc)
        if (lastBudgetItem != null) {
            Long code = lastBudgetItem.getExpenseCategoryCode();
            String category = lastBudgetItem.getExpenseCategoryDescription();
            Float totalValue = 0F;
            List<BudgetItem> budgetItemList = new ArrayList<>();

            BudgetCategory budgetCategory = new BudgetCategory();
            budgetCategory.setExpenseCategoryDescription(category);
            budgetCategory.setExpenseCategoryCode(code);
            totalValue += lastBudgetItem.getItemValue();
            budgetItemList.add(lastBudgetItem);

            while (lastBudgetItem != null && lastBudgetItem.getExpenseCategoryCode().equals(code)) {
                lastBudgetItem = (BudgetItem) delegate.read();

                if (lastBudgetItem != null && lastBudgetItem.getExpenseCategoryCode().equals(code)) {
                    totalValue += lastBudgetItem.getItemValue();
                    budgetItemList.add(lastBudgetItem);
                }
            }

            budgetCategory.setTotalCategoryValue(totalValue);
            budgetCategory.setBudgetItemList(budgetItemList);

            return budgetCategory;

        } else {
            return null;
        }
    }

}
