/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.Tag;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.TagDAO;
import org.openmrs.api.impl.TagServiceImpl;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * This is a unit test, which verifies logic in TagService.
 */
public class TagServiceTest extends BaseModuleContextSensitiveTest {
	
	private static final String TAG_INITIAL_XML = "TagServiceDataset.xml";
	
	@InjectMocks
	TagServiceImpl basicModuleService;
	
	@Mock
	TagDAO dao;
	
	@Mock
	UserService userService;
	
	@Mock
	ConceptService conceptService;
	
	@Before
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAlltags_shouldFetchAllTags() throws Exception {
		executeDataSet(TAG_INITIAL_XML);
		List<Tag> list = Context.getService(TagService.class).getAllTags();
		assertEquals(13, list.size());
	}
	
	@Test
	public void getTagById_shouldFetchUniqueMatchingTag() throws Exception {
		executeDataSet(TAG_INITIAL_XML);
		Tag tag = Context.getService(TagService.class).getTagById(3);
		assertEquals(tag.getUuid(), "e12c432c-1b9f-343e-b332-f3ef6c88ad3f");
	}
	
	@Test
	public void getTags_shouldFetchListOfMatchingTags() throws Exception {
		executeDataSet(TAG_INITIAL_XML);
		List<Tag> tagList = Context.getService(TagService.class).getTags("FollowUp");
		assertEquals(tagList.size(), 4);
	}
	
	@Test
	public void object_existsMethod_shouldreturntrueifobjectexists() throws Exception {
		boolean object = Context.getService(TagService.class).object_exits("0dde1358-7fcf-4341-a330-f119241a46e8",
		    "org.openmrs.Concept");
		assertTrue(object);
	}
	
	@Test
	public void getTagsWithObjectParameter_shouldReturnListOfStringTags() throws Exception {
		executeDataSet(TAG_INITIAL_XML);
		Encounter encounter = Context.getEncounterService().getEncounterByUuid("e403fafb-e5e4-42d0-9d11-4f52e89d148c");
		List<String> tags = Context.getService(TagService.class).getTags(encounter);
		assertEquals(tags.size(), 3);
	}
	
	@Test
	public void testing_getTags() throws Exception {
		executeDataSet(TAG_INITIAL_XML);
		List<String> types = new ArrayList<String>();
		types.add("org.openmrs.Encounter");
		List<String> tags = new ArrayList<String>();
		tags.add("Initial");
		tags.add("FollowUp");
		List<Tag> tagList = Context.getService(TagService.class).getTags(types, tags, true);
		assertEquals(tagList.size(), 4);
	}
	
	@Test
	public void addTag_shouldAddTagToObject() throws Exception {
		executeDataSet(TAG_INITIAL_XML);
		Obs obs = Context.getObsService().getObsByUuid("2f616900-5e7c-4667-9a7f-dcb260abf1de");
		Tag tag1 = Context.getService(TagService.class).addTag(obs, "Important");
		assertEquals(tag1.getTag(), "Important");
		assertEquals(tag1.getObjectType(), "org.openmrs.Obs");
		assertEquals(tag1.getObjectUuid(), obs.getUuid());
	}
}
