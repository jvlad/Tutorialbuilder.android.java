package com.freeraven.tutorialbuilder.pagecomponent.subtitle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freeraven.tutorialbuilder.R;
import com.freeraven.tutorialbuilder.pagecomponent.PageComponent;

/**
 * Created by Vlad Zamskoi (v.zamskoi@gmail.com) on 9/13/16.
 */
public class SubtitleComponent implements PageComponent {
    private SubtitleContent content;

    public void setContent(SubtitleContent content) {
        this.content = content;
    }

    public static PageComponent newInstance(String rowContentData) {
        SubtitleContent titleContent = new SubtitleContent();
        titleContent.setValue(rowContentData);
        SubtitleComponent component = new SubtitleComponent();
        component.setContent(titleContent);
        return component;
    }

    @Override
    public View render(LayoutInflater inflater, LinearLayout rootView) {
        TextView rendered = (TextView) inflater.inflate(R.layout.subtitle_component, rootView, false);
        rendered.setText(content.getValue());
        return rendered;
    }
}
