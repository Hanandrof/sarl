/**
 * $Id$
 * 
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 * 
 * Copyright (C) 2014-2020 the original authors or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License")
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
package io.sarl.sre.network.boot.internal.cli;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import org.eclipse.osgi.util.NLS;

/**
 * Messages for the SARL batch compiler.
 * 
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @ExcludeFromApidoc
 * @since 3.0.11.0
 */
@SarlSpecification("0.12")
@SarlElementType(10)
@SuppressWarnings("all")
public final class Messages extends NLS {
  private static final String BUNDLE_NAME = (Messages.class.getPackage().getName() + ".messages");
  
  static {
    NLS.initializeMessages(Messages.BUNDLE_NAME, Messages.class);
  }
  
  public static String VersionCommand_0;
  
  public static String VersionCommand_1;
  
  public static String VersionCommandModuleProvider_0;
  
  private Messages() {
  }
}
