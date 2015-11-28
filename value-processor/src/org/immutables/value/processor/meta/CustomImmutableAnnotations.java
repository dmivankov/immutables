package org.immutables.value.processor.meta;

import java.nio.charset.StandardCharsets;
import com.google.common.io.Resources;
import java.net.URL;
import java.util.Enumeration;
import java.io.IOException;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.List;

public final class CustomImmutableAnnotations {
  private CustomImmutableAnnotations() {}

  private static final String ANNOTATIONS_OPTION_KEY = "META-INF/annotations/org.immutables.value.immutable";

  private static final Splitter ADDITIONAL_ANNOTATION_SPLITTER = Splitter.on("\n")
      .omitEmptyStrings()
      .trimResults();

  private static final ImmutableSet<String> ANNOTATIONS = findExtensionAnnotations();

  public static ImmutableSet<String> annotations() {
    return ANNOTATIONS;
  }

  private static ImmutableSet<String> findExtensionAnnotations() {
    List<String> annotations = Lists.newArrayList();

    ClassLoader classLoader = CustomImmutableAnnotations.class.getClassLoader();
    try {
      Enumeration<URL> resources = classLoader.getResources(ANNOTATIONS_OPTION_KEY);
      while (resources.hasMoreElements()) {
        URL nextElement = resources.nextElement();
        String lines = Resources.toString(nextElement, StandardCharsets.UTF_8);
        annotations.addAll(ADDITIONAL_ANNOTATION_SPLITTER.splitToList(lines));
      }
    } catch (IOException cannotReadFromClasspath) {
      // we ignore this as we did or best effort
      // and there are no plans to halt whole compilation
    }
    return FluentIterable.from(annotations).toSet();
  }
}
