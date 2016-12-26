package vincent.task;

public class MyArrayList<AnyType> {
	private static final int DEFAULT_CAPACITY = 10;
	
	private int theSize;
	private AnyType [] theItems;
	public MyArrayList(){
		clear();
	}
	private void clear() {
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	//确保容器的容量,即使是size为0的情况,容器也是有一定容量的
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int newCapacity) {
		//如果需要的容量小于之前的size则不需要进行扩容
		if(newCapacity < theSize){
			return;
		}
		AnyType[] old = theItems;
		theItems = (AnyType[]) new Object[newCapacity];
		System.arraycopy(old, 0, theItems, 0, old.length);
	}
	
	//获取集合的大小
	public int size(){
		return theSize;
	}
	//判断集合是否为空
	public boolean isEmpty(){
		return theSize == 0;
	}
	
	//去除集合多余的容量
	public void trimToSize(){
		ensureCapacity(size());
	}
	//根据角标获取元素
	public AnyType get(int index){
		if(index < 0 || index >= size()){
			throw new RuntimeException("角标越界");
		}
		return theItems[index];
	}
	
	//根据角标更改元素,返回该位置上原有的元素
	public AnyType set(int index, AnyType newVal){
		if(index < 0 || index >= size()){
			throw new RuntimeException("角标越界");
		}
		AnyType old = theItems[index];
		theItems[index] = newVal;
		return old;
	}
	
	public void add(int index,AnyType val){
		//如果数组的长度和size相等,则需要扩充容量
		//为什么一定要调用size()方法来获取size??不直接使用theSize属性??????
		if(theItems.length == size()){
			ensureCapacity(size()*2 + 1);
		}
		for(int i=theSize;i>index;i--){
			theItems[i] = theItems[i-1];
		}
		theItems[index] = val;
		theSize++;
	}
	
	public boolean add(AnyType val){
		add(theSize,val);
		return true;
	}
	
	public AnyType remove(int index){
		if(index < 0 || index >= size()){
			throw new RuntimeException("角标越界");
		}
		AnyType removedItem = theItems[index];
		//从指定位置后一位的元素,依次向前移动一位
		for(int i = index;i< theSize - 1;i++){
			theItems[i] = theItems[i+1];
		}
		theSize--;
		return removedItem;
	}
	
	public java.util.Iterator<AnyType> iterator(){
		return new MyIterator();
	}
	
	
	//实现类,在实现接口的同时,也指定了数据类型,下面的这种写法是错误的,一定要注意啊!!!!!!啊啊啊啊
	//private class MyIterator<AnyType> implements java.util.Iterator<AnyType>{
	private class MyIterator implements java.util.Iterator<AnyType>{

		private int currentIndex = 0;
		public boolean hasNext() {
			return currentIndex < theSize;
		}

		public AnyType next() {
			//这种写法不好,代码写法,应该尽量简单明了,尽量少的使用++或者--这种容易使人混淆的写法
			//return theItems[currentIndex++];
			AnyType next = theItems[currentIndex];
			currentIndex += 1;
			return next;
			//currentIndex++; 我简直就是一个sb,怎么能在return下面写语句那!!!!!
		}

		//移除迭代器返回的最后一个元素
		public void remove() {
			//在调用next()方法后,使用仅使用一次该方法,才是正确的使用方式.
			MyArrayList.this.remove(--currentIndex);
		}
		
		/*
		 * 注意:从迭代器指向的 collection 中移除
		 * 迭代器返回的最后一个元素（可选操作）。
		 * 每次调用 next 只能调用一次此方法。
		 * 如果进行迭代时用调用此方法之外的其他方式
		 * 修改了该迭代器所指向的 collection，
		 * 则迭代器的行为是不确定的。 
		 */
		
	}
	
	
}
