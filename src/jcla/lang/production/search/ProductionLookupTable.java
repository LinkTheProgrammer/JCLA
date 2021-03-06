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

package jcla.lang.production.search;

import jcla.lang.production.Production;
import jcla.lang.production.Production.Definition;
import jcla.lang.production.Token;
import jcla.lang.production.java.token.Tokens;

import java.util.List;
import java.util.function.Function;

/**
 * @author Andrew Porter
 */
public class ProductionLookupTable {

	private final Node[] roots = new Node[5];

	public ProductionLookupTable() {
		roots[0] = new Node();
		roots[1] = new Node();
		roots[2] = new Node();
		roots[3] = new Node();
		roots[4] = new Node();
	}

	public void add(Definition definition) {
		List<Definition> pattern = definition.getPattern();
		int tokenID;
		Node node = roots[tokenID = Tokens.getID(tokens.get(0))];
		int size = tokens.size();

		for (int i = 1; i < size - 1; i++) {
			Token current = tokens.get(i);
			node.setBranch(tokenID, node = new Node(node));
		}

		node.setBranch(size - 1, new Node(node)); // set the last Node to return the Production

	}

	public Navigator navigator() {
		return new Navigator(this);
	}


	class Node {

		protected final Node parent;
		protected final Node[] branches = new Node[5];

		Node(Node parent) {
			this.parent = parent;
		}

		/**
		 * Creates a new orphan Node (used for root)
		 */
		Node() {
			this(null);
		}

		Node getBranch(int index) {
			return branches[index];
		}

		void setBranch(int index, Node node) {
			branches[index] = node;
		}

	}

	public static class Navigator {

		private ProductionLookupTable table;
		private Node current;

		public Navigator(ProductionLookupTable table) {
			this.table = table;
		}

		public Navigator(ProductionLookupTable table, int[] address) {
			this.table = table;
			for (int i : address) {
				current = table.roots[address[i]];
			}
		}

		public Navigator(ProductionLookupTable table, Token[] address) {
			this.table = table;
			for (Token token : address) {
				current = table.roots[token.getID()];
			}
		}

		public Navigator next(int index) {
			current = current.getBranch(index);
			return this;
		}

		public Navigator next(Token token) {
			return next(token.getID());
		}

		public Navigator jump(int... address) {
			for (int index : address) {
				current = current.getBranch(index);
			}

			return this;
		}

		/**
		 * Jumps by Token directly (using {@link Token#getID()}).
		 *
		 * @param address the array of Tokens that points to the correct Production.
		 * @return a Navigator for navigating this ProductionLookupTable
		 */
		public Navigator jump(Token... address) {
			for (Token index : address) {
				current = current.getBranch(index.getID());
			}

			return this;
		}

		public void create(Production production, Function<Token[], Production> builder) {

		}


	}

}
