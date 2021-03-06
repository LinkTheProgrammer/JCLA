/*
 * Copyright (c) 2018 Andrew Porter.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute,
 *  sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 *  conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package jcla.lang.production.java;

import jcla.lang.production.AbstractProduction;
import jcla.lang.production.Production;

import java.util.List;

/**
 * @author Andrew Porter
 */
public final class DecimalIntegerLiteral extends AbstractProduction {

	public static final Definition DEFINITION = new Definition("DecimalIntegerLiteral", DecimalNumeral.DEFINITION, new Definition.ZeroOrMore(IntegerTypeSuffix.DEFINITION));

	public DecimalIntegerLiteral(DecimalNumeral numeral) {
		super(List.of(numeral));
	}

	public DecimalIntegerLiteral(DecimalNumeral numeral, IntegerTypeSuffix suffix) {
		super(List.of(numeral, suffix));
	}

	@Override
	public Production.Definition getDefinition() {
		return null;
	}
}
