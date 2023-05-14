package com.rpa.wawinterpreter.waw.actions;

import android.graphics.Bitmap;

import com.rpa.automationframework.controls.ImageBasedUiElement;
import com.rpa.automationframework.controls.UiElement;
import com.rpa.automationframework.internal.helper.ImageUtils;
import com.rpa.wawinterpreter.waw.internal.helper.ParserHelper;
import com.rpa.wawinterpreter.waw.selectors.Selector;

import org.json.JSONObject;

public class GetImageAction extends SelectorBasedAction {
    private final String path;

    public GetImageAction(JSONObject parameters) {
        try {
            this.selectors = ParserHelper.parseSelectors(parameters);
        } catch (Exception e) {
            throw new RuntimeException("GetImage action requires a valid selector");
        }

        try {
            this.path = parameters.getString("path");
        } catch (Exception e) {
            throw new RuntimeException("GetImage action requires a valid path to save the file");
        }
    }

    @Override
    public void execute() {
        for (Selector selector : selectors) {
            UiElement element = selector.getUiElement();

            if (element instanceof ImageBasedUiElement) {
                Bitmap bitmap = ((ImageBasedUiElement) element).getImage();
                ImageUtils.saveBitmap(bitmap, path);
                return;
            }
        }
    }
}
