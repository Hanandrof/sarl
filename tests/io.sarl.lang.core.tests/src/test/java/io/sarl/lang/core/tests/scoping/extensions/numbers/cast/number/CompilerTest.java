/*
 * $Id$
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014-2019 the original authors or authors.
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

package io.sarl.lang.core.tests.scoping.extensions.numbers.cast.number;

import org.eclipse.xtext.common.types.TypesPackage;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.sarl.lang.SARLVersion;
import io.sarl.lang.sarl.SarlPackage;
import io.sarl.lang.sarl.SarlScript;
import io.sarl.lang.validation.IssueCodes;
import io.sarl.tests.api.AbstractSarlTest;
import io.sarl.tests.api.MassiveCompilationSuite;
import io.sarl.tests.api.MassiveCompilationSuite.CompilationTest;
import io.sarl.tests.api.MassiveCompilationSuite.Context;

/**
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 * @see "https://github.com/eclipse/xtext-extras/issues/186"
 */
@RunWith(MassiveCompilationSuite.class)
@SuppressWarnings("all")
public class CompilerTest extends AbstractSarlTest {

	private static final String AS_BYTE_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : byte {",
			"    left as byte",
			"  }",
			"}");

	private static final String AS_BYTE_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public byte fct(final Number left) {",
			"    return (left == null ? 0 : left.byteValue());",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_byte_issues() throws Exception {
		validate(file(AS_BYTE_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'byteValue'");
	}

	@CompilationTest
	public static void as_byte(Context ctx) throws Exception {
		ctx.compileTo(AS_BYTE_SARL, AS_BYTE_JAVA);
	}

	private static final String AS_SHORT_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : short {",
			"    left as short",
			"  }",
			"}");

	private static final String AS_SHORT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public short fct(final Number left) {",
			"    return (left == null ? 0 : left.shortValue());",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_short_issues() throws Exception {
		validate(file(AS_SHORT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'shortValue'");
	}

	@CompilationTest
	public static void as_short(Context ctx) throws Exception {
		ctx.compileTo(AS_SHORT_SARL, AS_SHORT_JAVA);
	}

	private static final String AS_INT_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : int {",
			"    left as int",
			"  }",
			"}");

	private static final String AS_INT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public int fct(final Number left) {",
			"    return (left == null ? 0 : left.intValue());",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_int_issues() throws Exception {
		validate(file(AS_INT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'intValue'");
	}

	@CompilationTest
	public static void as_int(Context ctx) throws Exception {
		ctx.compileTo(AS_INT_SARL, AS_INT_JAVA);
	}

	private static final String AS_LONG_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : long {",
			"    left as long",
			"  }",
			"}");

	private static final String AS_LONG_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public long fct(final Number left) {",
			"    return (left == null ? 0 : left.longValue());",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_long_issues() throws Exception {
		validate(file(AS_LONG_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'longValue'");
	}

	@CompilationTest
	public static void as_long(Context ctx) throws Exception {
		ctx.compileTo(AS_LONG_SARL, AS_LONG_JAVA);
	}

	private static final String AS_FLOAT_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : float {",
			"    left as float",
			"  }",
			"}");

	private static final String AS_FLOAT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public float fct(final Number left) {",
			"    return (left == null ? 0 : left.floatValue());",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_float_issues() throws Exception {
		validate(file(AS_FLOAT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'floatValue'");
	}

	@CompilationTest
	public static void as_float(Context ctx) throws Exception {
		ctx.compileTo(AS_FLOAT_SARL, AS_FLOAT_JAVA);
	}

	private static final String AS_DOUBLE_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : double {",
			"    left as double",
			"  }",
			"}");

	private static final String AS_DOUBLE_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public double fct(final Number left) {",
			"    return (left == null ? 0 : left.doubleValue());",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_double_issues() throws Exception {
		validate(file(AS_DOUBLE_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'doubleValue'");
	}

	@CompilationTest
	public static void as_double(Context ctx) throws Exception {
		ctx.compileTo(AS_DOUBLE_SARL, AS_DOUBLE_JAVA);
	}

	private static final String AS_BYTE_OBJECT_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : Byte {",
			"    left as Byte",
			"  }",
			"}");

	private static final String AS_BYTE_OBJECT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public Byte fct(final Number left) {",
			"    return (left == null ? null : (left instanceof Byte ? (Byte) left : Byte.valueOf(left.byteValue())));",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_Byte_issues() throws Exception {
		validate(file(AS_BYTE_OBJECT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'toByte'");
	}

	@CompilationTest
	public static void as_Byte(Context ctx) throws Exception {
		ctx.compileTo(AS_BYTE_OBJECT_SARL, AS_BYTE_OBJECT_JAVA);
	}

	private static final String AS_SHORT_OBJECT_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : Short {",
			"    left as Short",
			"  }",
			"}");

	private static final String AS_SHORT_OBJECT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public Short fct(final Number left) {",
			"    return (left == null ? null : (left instanceof Short ? (Short) left : Short.valueOf(left.shortValue())));",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_Short_issues() throws Exception {
		validate(file(AS_SHORT_OBJECT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'toShort'");
	}

	@CompilationTest
	public static void as_Short(Context ctx) throws Exception {
		ctx.compileTo(AS_SHORT_OBJECT_SARL, AS_SHORT_OBJECT_JAVA);
	}

	private static final String AS_INTEGER_OBJECT_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : Integer {",
			"    left as Integer",
			"  }",
			"}");

	private static final String AS_INTEGER_OBJECT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public Integer fct(final Number left) {",
			"    return (left == null ? null : (left instanceof Integer ? (Integer) left : Integer.valueOf(left.intValue())));",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_Integer_issues() throws Exception {
		validate(file(AS_INTEGER_OBJECT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'toInteger'");
	}

	@CompilationTest
	public static void as_Integer(Context ctx) throws Exception {
		ctx.compileTo(AS_INTEGER_OBJECT_SARL, AS_INTEGER_OBJECT_JAVA);
	}

	private static final String AS_LONG_OBJECT_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : Long {",
			"    left as Long",
			"  }",
			"}");

	private static final String AS_LONG_OBJECT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public Long fct(final Number left) {",
			"    return (left == null ? null : (left instanceof Long ? (Long) left : Long.valueOf(left.longValue())));",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_Long_issues() throws Exception {
		SarlScript script = file(AS_LONG_OBJECT_SARL);
		validate(script)
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION);
	}

	@CompilationTest
	public static void as_Long(Context ctx) throws Exception {
		ctx.compileTo(AS_LONG_OBJECT_SARL, AS_LONG_OBJECT_JAVA);
	}

	private static final String AS_FLOAT_OBJECT_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : Float {",
			"    left as Float",
			"  }",
			"}");

	private static final String AS_FLOAT_OBJECT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public Float fct(final Number left) {",
			"    return (left == null ? null : (left instanceof Float ? (Float) left : Float.valueOf(left.floatValue())));",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_Float_issues() throws Exception {
		validate(file(AS_FLOAT_OBJECT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'toFloat'");
	}

	@CompilationTest
	public static void as_Float(Context ctx) throws Exception {
		ctx.compileTo(AS_FLOAT_OBJECT_SARL, AS_FLOAT_OBJECT_JAVA);
	}

	private static final String AS_DOUBLE_OBJECT_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : Double {",
			"    left as Double",
			"  }",
			"}");

	private static final String AS_DOUBLE_OBJECT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public Double fct(final Number left) {",
			"    return (left == null ? null : (left instanceof Double ? (Double) left : Double.valueOf(left.doubleValue())));",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_Double_issues() throws Exception {
		validate(file(AS_DOUBLE_OBJECT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'toDouble'");
	}

	@CompilationTest
	public static void as_Double(Context ctx) throws Exception {
		ctx.compileTo(AS_DOUBLE_OBJECT_SARL, AS_DOUBLE_OBJECT_JAVA);
	}

	private static final String AS_ATOMICINTEGER_OBJECT_SARL = multilineString(
			"import java.util.concurrent.atomic.AtomicInteger",
			"class A {",
			"  def fct(left : Number) : AtomicInteger {",
			"    left as AtomicInteger",
			"  }",
			"}");

	private static final String AS_ATOMICINTEGER_OBJECT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import java.util.concurrent.atomic.AtomicInteger;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public AtomicInteger fct(final Number left) {",
			"    return (left == null ? null : (left instanceof AtomicInteger ? (AtomicInteger) left : new AtomicInteger(left.intValue())));",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_AtomicInteger_issues() throws Exception {
		validate(file(AS_ATOMICINTEGER_OBJECT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION);
	}

	@CompilationTest
	public static void as_AtomicInteger(Context ctx) throws Exception {
		ctx.compileTo(AS_ATOMICINTEGER_OBJECT_SARL, AS_ATOMICINTEGER_OBJECT_JAVA);
	}

	private static final String AS_ATOMICLONG_OBJECT_SARL = multilineString(
			"import java.util.concurrent.atomic.AtomicLong",
			"class A {",
			"  def fct(left : Number) : AtomicLong {",
			"    left as AtomicLong",
			"  }",
			"}");

	private static final String AS_ATOMICLONG_OBJECT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import java.util.concurrent.atomic.AtomicLong;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public AtomicLong fct(final Number left) {",
			"    return (left == null ? null : (left instanceof AtomicLong ? (AtomicLong) left : new AtomicLong(left.longValue())));",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_AtomicLong_issues() throws Exception {
		validate(file(AS_ATOMICLONG_OBJECT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'toAtomicLong'");
	}

	@CompilationTest
	public static void as_AtomicLong(Context ctx) throws Exception {
		ctx.compileTo(AS_ATOMICLONG_OBJECT_SARL, AS_ATOMICLONG_OBJECT_JAVA);
	}

	private static final String AS_ATOMICDOUBLE_OBJECT_SARL = multilineString(
			"import com.google.common.util.concurrent.AtomicDouble",
			"class A {",
			"  def fct(left : Number) : AtomicDouble {",
			"    left as AtomicDouble",
			"  }",
			"}");

	private static final String AS_ATOMICDOUBLE_OBJECT_JAVA = multilineString(
			"import com.google.common.util.concurrent.AtomicDouble;",
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public AtomicDouble fct(final Number left) {",
			"    return (left == null ? null : (left instanceof AtomicDouble ? (AtomicDouble) left : new AtomicDouble(left.doubleValue())));",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_AtomicDouble_issues() throws Exception {
		validate(file(AS_ATOMICDOUBLE_OBJECT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION);
	}

	@CompilationTest
	public static void as_AtomicDouble(Context ctx) throws Exception {
		ctx.compileTo(AS_ATOMICDOUBLE_OBJECT_SARL, AS_ATOMICDOUBLE_OBJECT_JAVA);
	}

	private static final String AS_NUMBER_OBJECT_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : Number {",
			"    left as Number",
			"  }",
			"}");

	private static final String AS_NUMBER_OBJECT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public Number fct(final Number left) {",
			"    return left;",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_Number_issues() throws Exception {
		validate(file(AS_NUMBER_OBJECT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST,
				"Number to Number")
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION);
	}

	@CompilationTest
	public static void as_Number(Context ctx) throws Exception {
		ctx.compileTo(AS_NUMBER_OBJECT_SARL, AS_NUMBER_OBJECT_JAVA);
	}

	private static final String AS_STRING_OBJECT_SARL = multilineString(
			"class A {",
			"  def fct(left : Number) : String {",
			"    left as String",
			"  }",
			"}");

	private static final String AS_STRING_OBJECT_JAVA = multilineString(
			"import io.sarl.lang.annotation.SarlElementType;",
			"import io.sarl.lang.annotation.SarlSpecification;",
			"import io.sarl.lang.annotation.SyntheticMember;",
			"import org.eclipse.xtext.xbase.lib.Pure;",
			"",
			"@SarlSpecification(\"" + SARLVersion.SPECIFICATION_RELEASE_VERSION_STRING + "\")",
			"@SarlElementType(" + SarlPackage.SARL_CLASS + ")",
			"@SuppressWarnings(\"all\")",
			"public class A {",
			"  @Pure",
			"  public String fct(final Number left) {",
			"    return (left == null ? null : left.toString());",
			"  }",
			"  ",
			"  @SyntheticMember",
			"  public A() {",
			"    super();",
			"  }",
			"}",
			"");

	@Test
	public void as_String_issues() throws Exception {
		validate(file(AS_STRING_OBJECT_SARL))
		.assertNoErrors(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertWarning(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION,
				"'toString'");
	}

	@CompilationTest
	public static void as_String(Context ctx) throws Exception {
		ctx.compileTo(AS_STRING_OBJECT_SARL, AS_STRING_OBJECT_JAVA);
	}

	private static final String AS_UUID_OBJECT_SARL = multilineString(
			"import java.util.UUID",
			"class A {",
			"  def fct(left : Number) : UUID {",
			"    left as UUID",
			"  }",
			"}");

	@Test
	public void as_UUID_issues() throws Exception {
		validate(file(AS_UUID_OBJECT_SARL))
		.assertError(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.INVALID_CAST,
				"Number to UUID")
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				org.eclipse.xtext.xbase.validation.IssueCodes.OBSOLETE_CAST)
		.assertNoWarnings(
				TypesPackage.eINSTANCE.getJvmParameterizedTypeReference(),
				IssueCodes.POTENTIAL_INEFFICIENT_VALUE_CONVERSION);
	}

}
