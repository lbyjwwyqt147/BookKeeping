package pers.liujunyi.bookkeeping.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/***
 * 文件名称: AtomicFloat.java
 * 文件描述: 由于Atomic没有提供Float 需要自己实现
 * 公 司: 
 * 内容摘要:  线程安全的Float
 * 其他说明:
 * 完成日期:2016年10月08日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class AtomicFloat extends Number {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AtomicInteger bits;

	public AtomicFloat() {
		this(0f);
	}

	public AtomicFloat(float initialValue) {
		bits = new AtomicInteger(Float.floatToIntBits(initialValue));
	}

	public final float addAndGet(float delta) {
		float expect;
		float update;
		do {
			expect = get();
			update = expect + delta;
		} while (!this.compareAndSet(expect, update));

		return update;
	}

	public final float decAndGet(float delta) {
		float expect;
		float update;
		do {
			expect = get();
			update = expect - delta;
		} while (!this.compareAndSet(expect, update));

		return update;
	}

	public final float getAndAdd(float delta) {
		float expect;
		float update;
		do {
			expect = get();
			update = expect + delta;
		} while (!this.compareAndSet(expect, update));

		return expect;
	}

	public final float getAndDecrement() {
		return getAndAdd(-1);
	}

	public final float decrementAndGet() {
		return addAndGet(-1);
	}

	public final float getAndIncrement() {
		return getAndAdd(1);
	}

	public final float incrementAndGet() {
		return addAndGet(1);
	}

	public final float getAndSet(float newValue) {
		float expect;
		do {
			expect = get();
		} while (!this.compareAndSet(expect, newValue));

		return expect;
	}

	public final boolean compareAndSet(float expect, float update) {
		return bits.compareAndSet(Float.floatToIntBits(expect),
				Float.floatToIntBits(update));
	}

	public final void set(float newValue) {
		bits.set(Float.floatToIntBits(newValue));
	}

	public final float get() {
		return Float.intBitsToFloat(bits.get());
	}

	public float floatValue() {
		return get();
	}

	public double doubleValue() {
		return (double) floatValue();
	}

	public int intValue() {
		return (int) get();
	}

	public long longValue() {
		return (long) get();
	}

	public String toString() {
		return Float.toString(get());
	}

}
