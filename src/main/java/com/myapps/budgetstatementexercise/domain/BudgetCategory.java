package com.myapps.budgetstatementexercise.domain;

import java.util.List;

public class BudgetCategory {

    private Long expenseCategoryCode;
    private String expenseCategoryDescription;
    private Float totalCategoryValue;
    private List<BudgetItem> budgetItemList;

    public Long getExpenseCategoryCode() {
        return expenseCategoryCode;
    }

    public void setExpenseCategoryCode(Long expenseCategoryCode) {
        this.expenseCategoryCode = expenseCategoryCode;
    }

    public String getExpenseCategoryDescription() {
        return expenseCategoryDescription;
    }

    public void setExpenseCategoryDescription(String expenseCategoryDescription) {
        this.expenseCategoryDescription = expenseCategoryDescription;
    }

    public Float getTotalCategoryValue() {
        return totalCategoryValue;
    }

    public void setTotalCategoryValue(Float totalCategoryValue) {
        this.totalCategoryValue = totalCategoryValue;
    }

    public List<BudgetItem> getBudgetItemList() {
        return budgetItemList;
    }

    public void setBudgetItemList(List<BudgetItem> budgetItemList) {
        this.budgetItemList = budgetItemList;
    }

    @Override
    public String toString() {
        return "BudgetCategory{" +
            "expenseCategoryCode=" + expenseCategoryCode +
            ", expenseCategoryDescription='" + expenseCategoryDescription +
            "', totalCategoryValue=" + totalCategoryValue +
            ", budgetItemList=" + budgetItemList +
            "}";
    }
}
