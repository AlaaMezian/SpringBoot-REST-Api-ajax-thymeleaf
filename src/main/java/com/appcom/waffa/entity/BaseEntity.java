package com.appcom.waffa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@MappedSuperclass
public class BaseEntity {


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_ts")
	protected Date createdTs;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_ts")
	protected Date updatedTs;

    public BaseEntity(){
    	this.createdTs = this.updatedTs = new Date();
    }
}
