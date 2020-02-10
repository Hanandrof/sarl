/*
 * $Id$
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014-2020 the original authors or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.sarl.lang.typesystem;

import java.util.regex.Pattern;

import javax.inject.Singleton;

import org.eclipse.xtend.core.xtend.XtendFunction;
import org.eclipse.xtext.common.types.JvmOperation;

/**
 * Test if names are for pure or not pure operations.
 *
 * <p>This implementation assumes that any function
 * with a name starting with "get", "is", "has" is a pure function.
 * It also assumes that "equals", "hashCode", "clone" and "toString" are also pure functions.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @since 0.6
 */
@Singleton
public class PureOperationNameValidator implements IPureOperationNameValidator {

	/** Regular expression patterns that matches the names of functions usually
	 * considered as pure.
	 */
	public static final String[] SPECIAL_PURE_FUNCTION_NAME_PATTERNS = {
		"clone", //$NON-NLS-1$
		"contains(?:[A-Z1-9_][a-zA-Z1-9_]*)?", //$NON-NLS-1$
		"equals", //$NON-NLS-1$
		"get(?:[A-Z1-9_][a-zA-Z1-9_]*)?", //$NON-NLS-1$
		"has[A-Z1-9_][a-zA-Z1-9_]*", //$NON-NLS-1$
		"hashCode", //$NON-NLS-1$
		"is[A-Z1-9_][a-zA-Z1-9_]*", //$NON-NLS-1$
		"iterator", //$NON-NLS-1$
		"length", //$NON-NLS-1$
		"to[A-Z1-9_][a-zA-Z1-9_]*", //$NON-NLS-1$
		"size", //$NON-NLS-1$
	};

	/** Regular expression patterns that matches the names of functions usually
	 * considered as not pure.
	 */
	public static final String[] SPECIAL_NOTPURE_FUNCTION_NAME_PATTERNS = {
		"set(?:[A-Z1-9].*)?", //$NON-NLS-1$
		"print(?:(?:ln)|(?:[A-Z1-9].*))?", //$NON-NLS-1$
	};

	private Pattern purePattern;

	private Pattern notPurePattern;

	/** Construct the helper.
	 *
	 * @see #SPECIAL_PURE_FUNCTION_NAME_PATTERNS
	 * @see #SPECIAL_NOTPURE_FUNCTION_NAME_PATTERNS
	 */
	public PureOperationNameValidator() {
		this.purePattern = buildPurePattern(SPECIAL_PURE_FUNCTION_NAME_PATTERNS);
		this.notPurePattern = buildPurePattern(SPECIAL_NOTPURE_FUNCTION_NAME_PATTERNS);
	}

	/** Construct the helper.
	 *
	 * @param additionalSpecialPureFunctionNamePatterns the patterns for the functions that are considered as pure functions.
	 * @see #SPECIAL_PURE_FUNCTION_NAME_PATTERNS
	 * @see #SPECIAL_NOTPURE_FUNCTION_NAME_PATTERNS
	 */
	public PureOperationNameValidator(String... additionalSpecialPureFunctionNamePatterns) {
		this.purePattern = buildPurePattern(SPECIAL_PURE_FUNCTION_NAME_PATTERNS, additionalSpecialPureFunctionNamePatterns);
		this.notPurePattern = buildPurePattern(SPECIAL_NOTPURE_FUNCTION_NAME_PATTERNS);
	}

	private static Pattern buildPurePattern(String[] patterns, String... additionalPatterns) {
		final StringBuilder fullPattern = new StringBuilder();
		fullPattern.append("^"); //$NON-NLS-1$
		boolean hasPattern = false;
		for (final String pattern : patterns) {
			if (hasPattern) {
				fullPattern.append("|"); //$NON-NLS-1$
			} else {
				hasPattern = true;
			}
			fullPattern.append("(?:"); //$NON-NLS-1$
			fullPattern.append(pattern);
			fullPattern.append(")"); //$NON-NLS-1$
		}
		if (additionalPatterns != null && additionalPatterns.length > 0) {
			for (final String pattern : additionalPatterns) {
				if (hasPattern) {
					fullPattern.append("|"); //$NON-NLS-1$
				} else {
					hasPattern = true;
				}
				fullPattern.append("(?:"); //$NON-NLS-1$
				fullPattern.append(pattern);
				fullPattern.append(")"); //$NON-NLS-1$
			}
		}
		fullPattern.append("$"); //$NON-NLS-1$
		return Pattern.compile(fullPattern.toString());
	}

	/** Replies if the given operation has a name which is assumed to be for a pure function by default.
	 *
	 * @param name the simple name of the operation.
	 * @return {@code true} if the operation has a side effects.
	 */
	protected boolean isNamePatternForPureOperation(String name) {
		return name != null && this.purePattern.matcher(name).matches();
	}

	@Override
	public boolean isNamePatternForPureOperation(JvmOperation operation) {
		return isNamePatternForPureOperation(operation.getSimpleName());
	}

	@Override
	public boolean isNamePatternForPureOperation(XtendFunction operation) {
		return isNamePatternForPureOperation(operation.getName());
	}

	/** Replies if the given expression has a side effect in the context of the given operation.
	 *
	 * @param name the simple name of the operation.
	 * @return {@code true} if the operation has a side effects.
	 */
	protected boolean isNamePatternForNotPureOperation(String name) {
		return name != null && this.notPurePattern.matcher(name).matches();
	}

	@Override
	public boolean isNamePatternForNotPureOperation(JvmOperation operation) {
		return isNamePatternForNotPureOperation(operation.getSimpleName());
	}

	@Override
	public boolean isNamePatternForNotPureOperation(XtendFunction operation) {
		return isNamePatternForNotPureOperation(operation.getName());
	}

}
