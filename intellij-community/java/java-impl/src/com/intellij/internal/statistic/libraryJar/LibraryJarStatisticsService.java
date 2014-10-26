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
package com.intellij.internal.statistic.libraryJar;

import com.intellij.facet.frameworks.SettingsConnectionService;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.net.HttpConfigurable;
import com.intellij.util.xmlb.XmlSerializationException;
import com.intellij.util.xmlb.XmlSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Ivan Chirkov
 */
public class LibraryJarStatisticsService extends SettingsConnectionService {

  private static final String FILE_NAME = "library-jar-statistics.xml";
  private static final String DEFAULT_SETTINGS_URL = "http://jetbrains.com/idea/" + FILE_NAME;
  private static final String DEFAULT_SERVICE_URL = "http://frameworks.jetbrains.com/statistics";

  private static final LibraryJarStatisticsService myInstance = new LibraryJarStatisticsService();
  private LibraryJarDescriptor[] myDescriptors;

  public static LibraryJarStatisticsService getInstance() {
    return myInstance;
  }

  protected LibraryJarStatisticsService() {
    super(DEFAULT_SETTINGS_URL, DEFAULT_SERVICE_URL);
  }

  @NotNull
  public LibraryJarDescriptor[] getTechnologyDescriptors() {
    if (myDescriptors == null) {
      final URL url = createVersionsUrl();
      if (url == null) return new LibraryJarDescriptor[0];
      final LibraryJarDescriptors descriptors = deserialize(url);
      myDescriptors = descriptors == null ? new LibraryJarDescriptor[0] : descriptors.getDescriptors();
    }
    return myDescriptors;
  }

  @Nullable
  private static LibraryJarDescriptors deserialize(@Nullable URL url) {
    if (url == null) return null;

    LibraryJarDescriptors libraryJarDescriptors = null;
    try {
      libraryJarDescriptors = XmlSerializer.deserialize(url, LibraryJarDescriptors.class);
    }
    catch (XmlSerializationException e) {
      //
    }
    return libraryJarDescriptors;
  }

  @Nullable
  private URL createVersionsUrl() {
    final String serviceUrl = getServiceUrl();
    if (StringUtil.isNotEmpty(serviceUrl)) {
      try {
        final String url = serviceUrl + "/" + FILE_NAME;
        HttpConfigurable.getInstance().prepareURL(url);

        return new URL(url);
      }
      catch (MalformedURLException ignored) {
      }
      catch (IOException e) {
        // no route to host, unknown host, etc.
      }
    }

    return null;
  }
}