package com.springapp.mvc.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by jin on 15. 10. 21..
 */

@Entity
@Table(name="test", catalog = "hornetdb")
public class TestVo implements Serializable{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="testNo", unique=true, nullable=false)
    private int testNo;

    @Column(name="testName")
    private String testName;

    @Column(name="testNumber")
    private int testNumber;

    @Column(name="testStatus")
    private boolean testStatus;

    @Column(name="testDate")
    private Date testDate;

    public int getTestNo() {
        return testNo;
    }

    public void setTestNo(int testNo) {
        this.testNo = testNo;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(int testNumber) {
        this.testNumber = testNumber;
    }

    public boolean isTestStatus() {
        return testStatus;
    }

    public void setTestStatus(boolean testStatus) {
        this.testStatus = testStatus;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    @Override
    public String toString() {
        return "TestVo{" +
                "testNo=" + testNo +
                ", testName='" + testName + '\'' +
                ", testNumber=" + testNumber +
                ", testStatus=" + testStatus +
                ", testDate=" + testDate +
                '}';
    }
}
