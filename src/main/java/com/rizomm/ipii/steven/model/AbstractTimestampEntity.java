package com.rizomm.ipii.steven.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class AbstractTimestampEntity ...
 *
 * @author Steven Dieu
 * Created on 08/01/2017
 */
@MappedSuperclass
public abstract class AbstractTimestampEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    private Date updated;

    /**
     * Method onCreate ...
     */
    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }

    /**
     * Method onUpdate ...
     */
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}