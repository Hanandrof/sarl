/*
 * $Id$
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014-2017 the original authors or authors.
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

package io.sarl.maven.docs.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/** Tools for generating markdown from different sources.
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @since 0.6
 */
public final class MarkdownExtensions {

	private static final String DEFAULT_LONG_OPT_PREFIX = "--"; //$NON-NLS-1$

	private static final String DEFAULT_OPT_PREFIX = "-"; //$NON-NLS-1$

	private MarkdownExtensions() {
		//
	}

	/** Render the option help to a Mardown table.
	 *
	 * @param options the options.
	 * @return the markdown table.
	 */
	public static String renderToMarkdown(Options options) {
		if (options == null) {
			return ""; //$NON-NLS-1$
		}
		final List<Option> optList = new ArrayList<>(options.getOptions());
		Collections.sort(optList, new OptionComparator());

		final StringBuilder buffer = new StringBuilder();
		for (final Option option : optList) {
			buffer.append("| `"); //$NON-NLS-1$
			if (option.getOpt() == null) {
				buffer.append(DEFAULT_LONG_OPT_PREFIX).append(option.getLongOpt());
			} else {
				buffer.append(DEFAULT_OPT_PREFIX).append(option.getOpt());
				if (option.hasLongOpt()) {
					buffer.append("`, `"); //$NON-NLS-1$
					buffer.append(DEFAULT_LONG_OPT_PREFIX).append(option.getLongOpt());
				}
			}

			if (option.hasArg()) {
				if (option.hasArgName()) {
					buffer.append(" <").append(option.getArgName()).append(">"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}

			buffer.append("` | "); //$NON-NLS-1$

			if (option.getDescription() != null) {
				String text = option.getDescription().replaceAll("[ \t\n\r\f]+", " "); //$NON-NLS-1$ //$NON-NLS-2$
				text = text.replaceAll("\\<", "&lt;");  //$NON-NLS-1$//$NON-NLS-2$
				text = text.replaceAll("\\>", "&gt;");  //$NON-NLS-1$//$NON-NLS-2$
				buffer.append(text);
			}

			buffer.append(" |\n"); //$NON-NLS-1$
		}

		return buffer.toString();
	}

	/** Comparator of command-line options.
	 *
	 * @author $Author: sgalland$
	 * @version $FullVersion$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 * @since 0.6
	 */
	private static class OptionComparator implements Comparator<Option> {

		OptionComparator() {
			//
		}

		private static String getKey(Option opt) {
			final String val = opt.getLongOpt();
			if (val == null) {
				return opt.getOpt();
			}
			return val;
		}

		@Override
		public int compare(Option opt1, Option opt2) {
			return getKey(opt1).compareToIgnoreCase(getKey(opt2));
		}

	}

}
