/*
 * $Id$
 *
 * File is automatically generated by the Xtext language generator.
 * Do not change it.
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright 2014-2016 the original authors and authors.
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
 */
package io.sarl.lang.codebuilder.builders;

import io.sarl.lang.sarl.SarlAnnotationType;
import io.sarl.lang.sarl.SarlCapacity;
import io.sarl.lang.sarl.SarlEvent;
import io.sarl.lang.sarl.SarlInterface;
import javax.inject.Inject;
import javax.inject.Named;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtend.core.xtend.XtendTypeDeclaration;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.common.types.JvmParameterizedTypeReference;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.util.Primitives;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.compiler.ImportManager;
import org.eclipse.xtext.xbase.imports.IImportsConfiguration;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReferenceFactory;
import org.eclipse.xtext.xbase.typesystem.references.StandardTypeReferenceOwner;
import org.eclipse.xtext.xbase.typesystem.util.CommonTypeComputationServices;

/** Abstract implementation of a builder for the Sarl language.
 */
@SuppressWarnings("all")
public abstract class AbstractBuilder {

	@Inject
	private CommonTypeComputationServices services;

	@Inject
	private ImportManager importManager;

	@Inject
	private TypeReferences typeReferences;

	@Inject
	private Primitives primitives;

	@Inject
	private IImportsConfiguration importsConfiguration;

	@Inject
	private IResourceFactory resourceFactory;

	private String fileExtension;

	@Inject
	public void setFileExtensions(@Named(Constants.FILE_EXTENSIONS) String fileExtensions) {
		this.fileExtension = fileExtensions.split("[:;,]+")[0];
	}

	/** Replies the script's file extension.
	 */
	@Pure
	public String getScriptFileExtension() {
		return this.fileExtension;
	}

	/** Replies the builder of type references.
	 *
	 * @return the type reference builder.
	 */
	@Pure
	protected TypeReferences getTypeReferences() {
		return this.typeReferences;
	}

	/** Replies the primitive type tools.
	 *
	 * @return the primitive type tools.
	 */
	@Pure
	protected Primitives getPrimitiveTypes() {
		return this.primitives;
	}

	/** Create a reference to the given type.
	 *
	 * @param context - the context.
	 * @param typeName - the name of the type.
	 * @return the type reference.
	 */
	protected JvmParameterizedTypeReference newTypeRef(EObject context, String typeName) {
		TypeReferences typeRefs = getTypeReferences();
		return newTypeRef(context, typeName, typeRefs.getTypeForName(typeName, context));
	}

	/** Create a reference to the given type.
	 *
	 * @param context - the context.
	 * @param type - the type.
	 * @return the type reference.
	 */
	protected JvmParameterizedTypeReference newTypeRef(EObject context, Class<?> type) {
		TypeReferences typeRefs = getTypeReferences();
		return newTypeRef(context, type.getName(), typeRefs.getTypeForName(type, context));
	}

	private JvmParameterizedTypeReference newTypeRef(EObject context, String typeName, JvmTypeReference typeReference) {
		if (!isTypeReference(typeReference) && !getPrimitiveTypes().isPrimitive(typeReference)) {
			TypeReferences typeRefs = getTypeReferences();
			for (String packageName : getImportsConfiguration().getImplicitlyImportedPackages((XtextResource) context.eResource())) {
				typeReference = typeRefs.getTypeForName(packageName + "." + typeName, context);
				if (isTypeReference(typeReference)) {
					getImportManager().addImportFor(typeReference.getType());
					return (JvmParameterizedTypeReference) typeReference;
				}
			}
		}
		if (!isTypeReference(typeReference)) {
			throw new TypeNotPresentException(typeName, null);
		}
		getImportManager().addImportFor(typeReference.getType());
		return (JvmParameterizedTypeReference) typeReference;
	}

	/** Replies if the first parameter is a subtype of the second parameter.
	 *
	 * @param context - the context.
	 * @param subType - the subtype to test.
	 * @param superType - the expected super type.
	 * @return the type reference.
	 */
	@Pure
	protected boolean isSubTypeOf(EObject context, JvmTypeReference subType, JvmTypeReference superType) {
		if (isTypeReference(superType) && isTypeReference(subType)) {
			StandardTypeReferenceOwner owner = new StandardTypeReferenceOwner(services, context);
			LightweightTypeReferenceFactory factory = new LightweightTypeReferenceFactory(owner, false);
			LightweightTypeReference reference = factory.toLightweightReference(subType);
			return reference.isSubtypeOf(superType.getType());
		}
		return false;
	}

	/** Replies if the given object is a valid type reference.
	 */
	@Pure
	protected boolean isTypeReference(JvmTypeReference typeReference) {
		return (typeReference != null && !typeReference.eIsProxy()
			&& typeReference.getType() != null && !typeReference.getType().eIsProxy());
	}

	/** Replies the import's configuration.
	 *
	 * @return the import's configuration.
	 */
	@Pure
	protected IImportsConfiguration getImportsConfiguration() {
		return this.importsConfiguration;
	}

	/** Compute a unused URI for a synthetic resource.
	 * @param resourceSet - the resource set in which the resource should be located.
	 * @return the uri.
	 */
	@Pure
	protected URI computeUnusedUri(ResourceSet resourceSet) {
		String name = "__synthetic";
		for (int i = 0; i < Integer.MAX_VALUE; ++i) {
			URI syntheticUri = URI.createURI(name + i + "." + getScriptFileExtension());
			if (resourceSet.getResource(syntheticUri, false) == null) {
				return syntheticUri;
			}
		}
		throw new IllegalStateException();
	}

	/** Replies the resource factory.
	 *
	 * @return the resource factory.
	 */
	@Pure
	protected IResourceFactory getResourceFactory() {
		return this.resourceFactory;
	}

	/** Replies if the type could contains functions with a body.
	 */
	@Pure
	protected boolean isActionBodyAllowed(XtendTypeDeclaration type) {
		return !(type instanceof SarlAnnotationType
			|| type instanceof SarlCapacity
			|| type instanceof SarlEvent
			|| type instanceof SarlInterface);
	}

	/** Replies the import manager that stores the imported types.
	 *
	 * @return the import manager.
	 */
	@Pure
	protected ImportManager getImportManager() {
		return this.importManager;
	}

}
