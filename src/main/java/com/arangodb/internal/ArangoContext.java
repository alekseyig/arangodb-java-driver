/*
 * DISCLAIMER
 *
 * Copyright 2018 ArangoDB GmbH, Cologne, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright holder is ArangoDB GmbH, Cologne, Germany
 */

package com.arangodb.internal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mark Vollmary
 *
 */
public class ArangoContext {

	private final Map<String, String> headerParam;

	public ArangoContext() {
		super();
		headerParam = new HashMap<String, String>();
	}

	public Map<String, String> getHeaderParam() {
		return headerParam;
	}

	public ArangoContext putHeaderParam(final String key, final String value) {
		if (value != null) {
			headerParam.put(key, value);
		}
		return this;
	}

}