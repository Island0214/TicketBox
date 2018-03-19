package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by island on 2018/3/19.
 */
@Entity
public class Discount {
    @Id
    private int discount_id;

    private int grade;

    /**
     * 0-100
     */
    private int discount;

    private int consumption;

    public Discount() {
    }

    public int getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(int discount_id) {
        this.discount_id = discount_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discount_id=" + discount_id +
                ", grade=" + grade +
                ", discount=" + discount +
                ", consumption=" + consumption +
                '}';
    }
}
