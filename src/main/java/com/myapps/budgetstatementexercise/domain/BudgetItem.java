package com.myapps.budgetstatementexercise.domain;

import java.time.LocalDate;

public class BudgetItem {

    private Long expenseCategoryCode;
    private String expenseCategoryDescription;
    private String itemDescription;
    private LocalDate itemDate;
    private Float itemValue;

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

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public LocalDate getItemDate() {
        return itemDate;
    }

    public void setItemDate(LocalDate itemDate) {
        this.itemDate = itemDate;
    }

    public Float getItemValue() {
        return itemValue;
    }

    public void setItemValue(Float itemValue) {
        this.itemValue = itemValue;
    }

    @Override
    public String toString() {
        return "BudgetItem{expenseCategoryCode=" + expenseCategoryCode +
            ", expenseCategoryDescription='" + expenseCategoryDescription +
            "', itemDescription='" + itemDescription +
            "', itemDate=" + itemDate +
            ", itemValue=" + itemValue +
            "}";
    }
}
