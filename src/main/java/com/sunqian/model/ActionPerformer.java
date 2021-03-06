package com.sunqian.model;

import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.sunqian.constvalue.ConstValue;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 转换特定对象管理类
 *
 * @author sunqian
 * @date 2019/6/10
 */
@Data
@NoArgsConstructor
public class ActionPerformer {

    private static volatile ActionPerformer actionPerformer;

    private Project project;
    private Editor editor;
    private Document document;
    private SelectionModel selectionModel;
    private CaretModel caretModel;
    private ConstValue constValue;

    private ActionPerformer(Project project, Editor editor) {
        this.project = project;
        this.editor = editor;
    }

    public static ActionPerformer getActionPerformer(Project project, Editor editor) {
        if (actionPerformer == null || actionPerformer.getProject() != project || actionPerformer.getEditor() != editor) {
            synchronized (ActionPerformer.class) {
                if (actionPerformer == null || actionPerformer.getProject() != project || actionPerformer.getEditor() != editor) {
                    actionPerformer = new ActionPerformer(project, editor);
                }
            }
        }
        return actionPerformer;
    }

    public Document getDocument() {
        return this.editor.getDocument();
    }

    public CaretModel getCaretModel() {
        return this.editor.getCaretModel();
    }

    public ConstValue getConstValue() {
        return ConstValue.getInstance();
    }

    public SelectionModel getSelectionModel() {
        return this.editor.getSelectionModel();
    }
}
