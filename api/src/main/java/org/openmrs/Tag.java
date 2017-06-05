/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Please note that a corresponding table schema must be created in liquibase.xml.
 */
//Uncomment 2 lines below if you want to make the Item class persistable, see also TagDaoTest and liquibase.xml
@Entity(name = "tag.Tag")
@Table(name = "tag")
public class Tag extends BaseOpenmrsData implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tag_id")
	private Integer id;
	
	@Column(name = "tag", length = 50, nullable = false)
	private String tagName;
	
	@Column(name = "object_uuid", length = 38, nullable = false)
	private String objectUuid;
	
	@Column(name = "object_type", length = 255, nullable = false)
	private String objectType;
	
	@ManyToOne
	@JoinColumn(name = "creator")
	private User Creator;
	
	@Column(name = "date_created", nullable = false)
	private Date dateCreated;
	
	@Column(name = "uuid", unique = true, nullable = false, length = 38)
	private String Uuid = UUID.randomUUID().toString();
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTagName() {
		return tagName;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public String getObjectUuid() {
		return objectUuid;
	}
	
	public void setObjectUuid(String objectUuid) {
		this.objectUuid = objectUuid;
	}
	
	public String getObjectType() {
		return objectType;
	}
	
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	@Override
	public User getCreator() {
		return Creator;
	}
	
	@Override
	public void setCreator(User creator) {
		Creator = creator;
	}
	
	@Override
	public Date getDateCreated() {
		return dateCreated;
	}
	
	@Override
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Override
	public String getUuid() {
		return Uuid;
	}
	
	@Override
	public void setUuid(String uuid) {
		Uuid = uuid;
	}
}
