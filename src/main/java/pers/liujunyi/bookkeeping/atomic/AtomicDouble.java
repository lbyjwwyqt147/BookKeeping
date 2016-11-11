package pers.liujunyi.bookkeeping.atomic;

import java.util.concurrent.atomic.AtomicLong;

/***
 * 文件名称: AtomicDouble.java
 * 文件描述: 由于Atomic没有提供Double 需要自己实现
 * 公 司: 
 * 内容摘要:  线程安全的Double
 * 其他说明:
 * 完成日期:2016年10月08日 
 * 修改记录:
 * @version 1.0
 * @author liujunyi
 */
public class AtomicDouble {

	private AtomicLong bits;

	public AtomicDouble() {
		this(0d);
	}

	public AtomicDouble(double initialValue) {
		bits = new AtomicLong(Double.doubleToLongBits(initialValue));
	}

	public final double addAndGet(double delta) {
		double expect;
		double update;
		do {
			expect = get();
			update = expect + delta;
		} while (!this.compareAndSet(expect, update));

		return update;
	}

	public final double decAndGet(double delta) {
		double expect;
		double update;
		do {
			expect = get();
			update = expect - delta;
		} while (!this.compareAndSet(expect, update));

		return update;
	}

	public final double getAndAdd(double delta) {
		double expect;
		double update;
		do {
			expect = get();
			update = expect + delta;
		} while (!this.compareAndSet(expect, update));

		return expect;
	}

	public final double getAndDecrement() {
		return getAndAdd(-1);
	}

	public final double decrementAndGet() {
		return addAndGet(-1);
	}

	public final double getAndIncrement() {
		return getAndAdd(1);
	}

	public final double incrementAndGet() {
		return addAndGet(1);
	}

	public final double getAndSet(double newValue) {
		double expect;
		do {
			expect = get();
		} while (!this.compareAndSet(expect, newValue));

		return expect;
	}

	public final boolean compareAndSet(double expect, double update) {
		return bits.compareAndSet(Double.doubleToLongBits(expect),
				Double.doubleToLongBits(update));
	}

	public final void set(double newValue) {
		bits.set(Double.doubleToLongBits(newValue));
	}

	public final double get() {
		return Double.valueOf(Double.longBitsToDouble(bits.get()));
	}

	public double DoubleValue() {
		return get();
	}

	public double doubleValue() {
		return (double) DoubleValue();
	}

	public int intValue() {
		return (int) get();
	}

	public long longValue() {
		return (long) get();
	}

	public String toString() {
		return Double.toString(get());
	}

}
