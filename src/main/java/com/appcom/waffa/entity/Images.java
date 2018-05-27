package com.appcom.waffa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="images")
@Getter
@Setter
public class Images {
   @Id
   @Column(name = "id")
   private Long id;
	
   @Column(name = "name")
	private String name;
   
   @Column(name = "type")
	private String type;
	
	@Lob
   @Column(name="pic")
   private byte[] pic;
}
