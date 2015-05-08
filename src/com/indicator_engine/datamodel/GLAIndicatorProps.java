package com.indicator_engine.datamodel;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Tanmaya Mahapatra on 08-05-2015.
 */
@Entity
@Table(name = "gla_IndicatorProps")
public class GLAIndicatorProps implements Serializable {

    @Id
    @Column(name = "props_id", unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "lex_time", nullable = false)
    private Timestamp last_executionTime;

    @Column(name = "execution_counter", nullable = false)
    private int totalExecutions;

    @Column(name = "short_name", nullable = false)
    private String short_name;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private GLAIndicator glaIndicator;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getLast_executionTime() {
        return last_executionTime;
    }

    public void setLast_executionTime(Timestamp last_executionTime) {
        this.last_executionTime = last_executionTime;
    }

    public int getTotalExecutions() {
        return totalExecutions;
    }

    public void setTotalExecutions(int totalExecutions) {
        this.totalExecutions = totalExecutions;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public GLAIndicator getGlaIndicator() {
        return glaIndicator;
    }

    public void setGlaIndicator(GLAIndicator glaIndicator) {
        this.glaIndicator = glaIndicator;
    }
}
