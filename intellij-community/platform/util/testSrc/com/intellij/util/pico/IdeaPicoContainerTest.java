/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.util.pico;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.picocontainer.MutablePicoContainer;

public class IdeaPicoContainerTest {
  private MutablePicoContainer myContainer;

  @Before
  public void setUp() throws Exception {
    myContainer = new IdeaPicoContainer();
  }

  @Test
  public void testUnregister() throws Exception {
    String key = "myObject";
    MyComponentClass instance = new MyComponentClass();
    myContainer.registerComponentInstance(key, instance);
    Assert.assertEquals(1, myContainer.getComponentAdaptersOfType(MyComponentClass.class).size());
    myContainer.unregisterComponent(key);
    Assert.assertTrue(myContainer.getComponentAdaptersOfType(MyComponentClass.class).isEmpty());
  }

  private static class MyComponentClass {}
}