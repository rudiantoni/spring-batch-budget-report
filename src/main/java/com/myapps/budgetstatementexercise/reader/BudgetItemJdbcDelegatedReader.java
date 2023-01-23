package com.myapps.budgetstatementexercise.reader;

import com.myapps.budgetstatementexercise.domain.BudgetCategory;
import com.myapps.budgetstatementexercise.domain.BudgetItem;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;

import java.util.ArrayList;
import java.util.List;

public class BudgetItemJdbcDelegatedReader implements ItemStreamReader<BudgetCategory> {

    private final JdbcPagingItemReader<Object> delegate;

    private BudgetItem lastBudgetItem;

    public BudgetItemJdbcDelegatedReader(JdbcPagingItemReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public BudgetCategory read() throws Exception {
        // Se o último não tiver sido definido
        if (lastBudgetItem == null) {
            lastBudgetItem = (BudgetItem) delegate.read();
        }

        // Se ainda houverem dados para ler ou não é EOF
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

            while(lastBudgetItem != null && lastBudgetItem.getExpenseCategoryCode().equals(code)) {
                lastBudgetItem = (BudgetItem) delegate.read();

                if (lastBudgetItem != null && lastBudgetItem.getExpenseCategoryCode().equals(code)) {
                    totalValue += lastBudgetItem.getItemValue();
                    budgetItemList.add(lastBudgetItem);
                }
            }

            budgetCategory.setTotalCategoryValue(totalValue);
            budgetCategory.setBudgetItemList(budgetItemList);

            return budgetCategory;

        }
        else {
            return null;
        }

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
}
