package com.rizomm.ipii.steven.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class AbstractTimestampEntity
 *
 * @author Steven Dieu
 *         Created on 08/01/2017
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

    /**
     * Method getCreated returns the created of this AbstractTimestampEntity object.
     *
     * @return the created (type Date) of this AbstractTimestampEntity object.
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Method setCreated sets the created of this AbstractTimestampEntity object.
     *
     * @param created the created of this AbstractTimestampEntity object.
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * Method getUpdated returns the updated of this AbstractTimestampEntity object.
     *
     * @return the updated (type Date) of this AbstractTimestampEntity object.
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * Method setUpdated sets the updated of this AbstractTimestampEntity object.
     *
     * @param updated the updated of this AbstractTimestampEntity object.
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}