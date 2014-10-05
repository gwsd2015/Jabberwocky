/*
 * Copyright 2000-2012 JetBrains s.r.o.
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
package com.intellij.designer.designSurface;

import com.intellij.designer.designSurface.tools.InputTool;
import com.intellij.designer.model.RadComponent;

import java.awt.*;

/**
 * @author Alexander Lobas
 */
public abstract class StaticDecorator extends ComponentDecorator {
  private final RadComponent myComponent;

  public StaticDecorator(RadComponent component) {
    myComponent = component;
  }

  @Override
  public final InputTool findTargetTool(DecorationLayer layer, RadComponent component, int x, int y) {
    return null;
  }

  public final void decorate(DecorationLayer layer, Graphics2D host) {
    decorate(layer, host, myComponent);
  }
}