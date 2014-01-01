/*
    Copyright 2013-2014 Immutables.org authors

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.immutables.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Abstract immutable classes annotated with this annotation will have repository generated to store
 * and retreive documents from MongoDB collection named by class name or explicitly named as
 * specified by {@link #value()}.
 * <p>
 * {@link GenerateRepository} requires marshaler for the annotated class, so one will be generated
 * regardless of the presense of @{@link GenerateMarshaler} annotation. However, care should be
 * taken for all attributes to be recursively marshalable by being either built-in types or
 * marshalable documents (annotated with @{@link GenerateMarshaler}) or ensuring some marhaling
 * {@link GenerateMarshaler#importRoutines() routines} exists to marshal types.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface GenerateRepository {
  /**
   * Specify document collection name. If not specified, then collection name will be given
   * automatically by document class name, i.e. {@code "myDocument"} for {@code MyDocument} class.
   */
  String value() default "";
}
