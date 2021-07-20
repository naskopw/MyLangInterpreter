package language_elements.expression;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.type_system.Type;
import prelexer.Position;

	public class StringLiteral extends Expression {
		private String value;

		public StringLiteral(Position pos, String value) {
			super(pos);
			this.value = value;
			setType(Type.STRING);
		}

		@Override
		public <T, A> T accept(TypedVisitor<T, A> v, A arg) {
			return v.visit(this, null);
		}

		@Override
		public <A> void accept(VoidVisitor<A> v, A arg) {
			v.visit(this, null);
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return "\"" + value.replaceAll("\n", "\\\\n") + "\"";
		}

	}