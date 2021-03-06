package jcla.classfile;

/**
 * @author link
 */
public enum InnerClassModifier {
	PUBLIC(0x0001),
	PRIVATE(0x0002),
	PROTECTED(0x0004),
	STATIC(0x0008),
	FINAL(0x0010),
	INTERFACE(0x0200),
	ABSTRACT(0x0400),
	SYNTHETIC(0x1000),
	ANNOTATION(0x2000),
	ENUM(0x4000);

	private final int flag;

	InnerClassModifier(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

	public static InnerClassModifier get(int flag) {
		switch (flag) {
			case 0x0001:
				return PUBLIC;
			case 0x0002:
				return PRIVATE;
			case 0x0004:
				return PROTECTED;
			case 0x0008:
				return STATIC;
			case 0x0010:
				return FINAL;
			case 0x0200:
				return INTERFACE;
			case 0x0400:
				return ABSTRACT;
			case 0x1000:
				return SYNTHETIC;
			case 0x2000:
				return ANNOTATION;
			case 0x4000:
				return ENUM;
			default:
				throw new IllegalArgumentException("Unknown nested class modifier: " + flag);
		}
	}

}
