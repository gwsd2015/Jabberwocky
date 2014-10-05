/*
 * Copyright 2000-2013 JetBrains s.r.o.
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
package org.jetbrains.plugins.groovy.editor.actions.joinLines;

import com.intellij.codeInsight.editorActions.JoinLinesHandlerDelegate;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.plugins.groovy.lang.lexer.GroovyTokenTypes;
import org.jetbrains.plugins.groovy.lang.psi.GroovyFileBase;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.*;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.blocks.GrOpenBlock;

public class GrJoinBlockStatementHandler implements JoinLinesHandlerDelegate {
  @Override
  public int tryJoinLines(Document document, PsiFile file, int start, int end) {
    if (!(file instanceof GroovyFileBase)) return CANNOT_JOIN;

    final PsiElement startElement = file.findElementAt(start);
    if (startElement == null || startElement.getNode().getElementType() != GroovyTokenTypes.mLCURLY) return CANNOT_JOIN;

    final PsiElement parent = startElement.getParent();
    if (!(parent instanceof GrOpenBlock)) return CANNOT_JOIN;

    final GrStatement[] statements = ((GrOpenBlock)parent).getStatements();
    if (statements.length != 1) return CANNOT_JOIN;

    final PsiElement parent1 = parent.getParent();
    if (!(parent1 instanceof GrBlockStatement)) return CANNOT_JOIN;

    final PsiElement parent2 = parent1.getParent();
    if (!(parent2 instanceof GrIfStatement) && !(parent2 instanceof GrWhileStatement) && !(parent2 instanceof GrForStatement)) {
      return CANNOT_JOIN;
    }

    final GrStatement statement = ((GrBlockStatement)parent1).replaceWithStatement(statements[0]);
    return statement.getTextRange().getStartOffset();
  }
}
