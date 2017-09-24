/*
 * Copyright 2010 Joachim Ansorg, mail@ansorg-it.com
 * File: BashFileTypeLoader.java, Class: BashFileTypeLoader
 * Last modified: 2010-03-24
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package technology.svelte.file;

import com.intellij.framework.FrameworkAvailabilityCondition;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;


public class SvelteFileTypeFactory extends FileTypeFactory {
    public void createFileTypes(@NotNull FileTypeConsumer consumer) {
        consumer.consume(SvelteFileType.SVELTE_FILE_TYPE, SvelteFileType.DEFAULT_EXTENSION+";html");
//        DataManager.getInstance()
//                .getDataContextFromFocus()
//                .doWhenDone((Consumer<DataContext>) dataContext -> {
//                    Project project = DataKeys.PROJECT.getData(dataContext);
//                    consumer.consume(SvelteFileType.SVELTE_FILE_TYPE, "html");
//                })
//                .getResult();
//        Project project = (Project) dataContext.getData(DataConstants.PROJECT);
//
    }
}
