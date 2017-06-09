/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.canonits.sample.component;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import jp.co.canonits.common.component.ExRadio;
import jp.co.canonits.common.component.tree.ExDefaultNode;

/**
 * 
 */
public class MyPanel extends Panel
{
    private static final long serialVersionUID = 1L;
	
	private static final List<String> SEARCH_ENGINES = Arrays.asList(new String[]{"Google","Bing","Baidu"});

    public MyPanel(String id, IModel<ExDefaultNode> foo)
    {
        super(id, foo);

        add(new ExRadio<ExDefaultNode>("radio", new PropertyModel<>(foo, "radioId")));

        add(new DropDownChoice<>("select", new PropertyModel<>(foo, "selectValue"), SEARCH_ENGINES));

        add(new TextField<ExDefaultNode>("text", new PropertyModel<>(foo, "textValue")));
    }
}
