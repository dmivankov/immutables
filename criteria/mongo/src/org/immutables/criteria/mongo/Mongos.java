/*
 * Copyright 2019 Immutables Authors and Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.immutables.criteria.mongo;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.immutables.criteria.expression.ExpressionConverter;
import org.immutables.criteria.expression.Expressions;

import java.util.Objects;

/**
 * Util methods for mongo adapter.
 */
final class Mongos {

  private Mongos() {}

  /**
   * Convert existing expression to Bson
   */
  static ExpressionConverter<Bson> converter(CodecRegistry registry) {
    Objects.requireNonNull(registry, "registry");
    return expression -> {
      if (Expressions.isNil(expression)) {
        return new Document();
      }
      MongoQueryVisitor visitor = new MongoQueryVisitor(registry);
      return expression.accept(visitor).asDocument();
    };
  }

}
