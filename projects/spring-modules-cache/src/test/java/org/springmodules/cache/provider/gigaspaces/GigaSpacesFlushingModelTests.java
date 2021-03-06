/*
* Copyright 2006 GigaSpaces, Inc.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.springmodules.cache.provider.gigaspaces;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import org.springmodules.AbstractEqualsHashCodeTestCase;
import org.springmodules.AssertExt;

/**
 * <p>
 * Unit Tests for <code>{@link GigaSpacesFlushingModel}</code>.
 * </p>
 *
 * @author Alex Ruiz
 */
public class GigaSpacesFlushingModelTests extends AbstractEqualsHashCodeTestCase {

  private GigaSpacesFlushingModel model;

  public GigaSpacesFlushingModelTests(String name) {
    super(name);
  }

  /**
   * @see org.springmodules.EqualsHashCodeTestCase#testEqualsHashCodeRelationship()
   */
  public void testEqualsHashCodeRelationship() {
    String[] cacheNames = { "service", "pojos" };

    model.setCacheNames(cacheNames);
    GigaSpacesFlushingModel model2 = new GigaSpacesFlushingModel(cacheNames);
    assertEqualsHashCodeRelationshipIsCorrect(model, model2);

    cacheNames = null;
    model.setCacheNames(cacheNames);
    model2.setCacheNames(cacheNames);
    assertEqualsHashCodeRelationshipIsCorrect(model, model2);

    boolean flushBeforeMethodExecution = true;
    model.setFlushBeforeMethodExecution(flushBeforeMethodExecution);
    model2.setFlushBeforeMethodExecution(flushBeforeMethodExecution);
    assertEqualsHashCodeRelationshipIsCorrect(model, model2);
  }

  /**
   * @see org.springmodules.EqualsHashCodeTestCase#testEqualsIsConsistent()
   */
  public void testEqualsIsConsistent() {
    String csvCacheNames = "dao";

    model.setCacheNames(csvCacheNames);
    GigaSpacesFlushingModel model2 = new GigaSpacesFlushingModel(csvCacheNames);
    assertEquals(model, model2);

    model2.setCacheNames(new String[0]);
    assertFalse(model.equals(model2));
  }

  /**
   * @see org.springmodules.EqualsHashCodeTestCase#testEqualsIsReflexive()
   */
  public void testEqualsIsReflexive() {
    assertEqualsIsReflexive(model);
  }

  /**
   * @see org.springmodules.EqualsHashCodeTestCase#testEqualsIsSymmetric()
   */
  public void testEqualsIsSymmetric() {
    boolean flushBeforeMethodExecution = true;
    model.setFlushBeforeMethodExecution(flushBeforeMethodExecution);

    GigaSpacesFlushingModel model2 = new GigaSpacesFlushingModel();
    model2.setFlushBeforeMethodExecution(flushBeforeMethodExecution);

    assertEqualsIsSymmetric(model, model2);
  }

  /**
   * @see org.springmodules.EqualsHashCodeTestCase#testEqualsIsTransitive()
   */
  public void testEqualsIsTransitive() {
    String[] cacheNames = { "webui" };

    model.setCacheNames(cacheNames);
    GigaSpacesFlushingModel model2 = new GigaSpacesFlushingModel(cacheNames);
    GigaSpacesFlushingModel model3 = new GigaSpacesFlushingModel(cacheNames);

    assertEqualsIsTransitive(model, model2, model3);
  }

  /**
   * @see org.springmodules.EqualsHashCodeTestCase#testEqualsNullComparison()
   */
  public void testEqualsNullComparison() {
    assertEqualsNullComparisonReturnsFalse(model);
  }

  public void testSetCacheNamesWithEmptyCsv() {
    model.setCacheNames("");
    AssertExt.assertEquals(new String[0], model.getCacheNames());
  }

  public void testSetCacheNamesWithNotEmptyCsv() {
    String cacheNames = "main,test";
    model.setCacheNames(cacheNames);

    String[] expected = StringUtils.commaDelimitedListToStringArray(cacheNames);
    AssertExt.assertEquals(expected, model.getCacheNames());
  }

  public void testSetCacheNamesWithNotEmptySet() {
    String[] cacheNames = { "main", "session" };
    model.setCacheNames(cacheNames);
    AssertExt.assertEquals(cacheNames, model.getCacheNames());
  }

  public void testSetCacheNamesWithNullCsv() {
    model.setCacheNames((String) null);
    assertNull(model.getCacheNames());
  }

  public void testToStringWithCacheNamesEqualToNull() {
    model.setCacheNames((String[]) null);
    model.setFlushBeforeMethodExecution(true);

    String actual = model.getClass().getName() + "@"
        + ObjectUtils.getIdentityHexString(model)
        + "[cacheNames=null, flushBeforeMethodExecution=true]";
    assertEquals(model.toString(), actual);
  }

  public void testToStringWithEmptyCacheNames() {
    model.setCacheNames(new String[0]);
    model.setFlushBeforeMethodExecution(true);

    String actual = model.getClass().getName() + "@"
        + ObjectUtils.getIdentityHexString(model)
        + "[cacheNames={}, flushBeforeMethodExecution=true]";
    assertEquals(model.toString(), actual);
  }

  public void testToStringWithNotEmptyCacheNames() {
    model.setCacheNames(new String[] { "main" });
    model.setFlushBeforeMethodExecution(true);

    String actual = model.getClass().getName() + "@"
        + ObjectUtils.getIdentityHexString(model)
        + "[cacheNames={'main'}, flushBeforeMethodExecution=true]";
    assertEquals(model.toString(), actual);
  }

  protected void setUp() {
    model = new GigaSpacesFlushingModel();
  }
}
