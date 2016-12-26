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
	
	//ȷ������������,��ʹ��sizeΪ0�����,����Ҳ����һ��������
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int newCapacity) {
		//�����Ҫ������С��֮ǰ��size����Ҫ��������
		if(newCapacity < theSize){
			return;
		}
		AnyType[] old = theItems;
		theItems = (AnyType[]) new Object[newCapacity];
		System.arraycopy(old, 0, theItems, 0, old.length);
	}
	
	//��ȡ���ϵĴ�С
	public int size(){
		return theSize;
	}
	//�жϼ����Ƿ�Ϊ��
	public boolean isEmpty(){
		return theSize == 0;
	}
	
	//ȥ�����϶��������
	public void trimToSize(){
		ensureCapacity(size());
	}
	//���ݽǱ��ȡԪ��
	public AnyType get(int index){
		if(index < 0 || index >= size()){
			throw new RuntimeException("�Ǳ�Խ��");
		}
		return theItems[index];
	}
	
	//���ݽǱ����Ԫ��,���ظ�λ����ԭ�е�Ԫ��
	public AnyType set(int index, AnyType newVal){
		if(index < 0 || index >= size()){
			throw new RuntimeException("�Ǳ�Խ��");
		}
		AnyType old = theItems[index];
		theItems[index] = newVal;
		return old;
	}
	
	public void add(int index,AnyType val){
		//�������ĳ��Ⱥ�size���,����Ҫ��������
		//Ϊʲôһ��Ҫ����size()��������ȡsize??��ֱ��ʹ��theSize����??????
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
			throw new RuntimeException("�Ǳ�Խ��");
		}
		AnyType removedItem = theItems[index];
		//��ָ��λ�ú�һλ��Ԫ��,������ǰ�ƶ�һλ
		for(int i = index;i< theSize - 1;i++){
			theItems[i] = theItems[i+1];
		}
		theSize--;
		return removedItem;
	}
	
	public java.util.Iterator<AnyType> iterator(){
		return new MyIterator();
	}
	
	
	//ʵ����,��ʵ�ֽӿڵ�ͬʱ,Ҳָ������������,���������д���Ǵ����,һ��Ҫע�Ⱑ!!!!!!��������
	//private class MyIterator<AnyType> implements java.util.Iterator<AnyType>{
	private class MyIterator implements java.util.Iterator<AnyType>{

		private int currentIndex = 0;
		public boolean hasNext() {
			return currentIndex < theSize;
		}

		public AnyType next() {
			//����д������,����д��,Ӧ�þ���������,�����ٵ�ʹ��++����--��������ʹ�˻�����д��
			//return theItems[currentIndex++];
			AnyType next = theItems[currentIndex];
			currentIndex += 1;
			return next;
			//currentIndex++; �Ҽ�ֱ����һ��sb,��ô����return����д�����!!!!!
		}

		//�Ƴ����������ص����һ��Ԫ��
		public void remove() {
			//�ڵ���next()������,ʹ�ý�ʹ��һ�θ÷���,������ȷ��ʹ�÷�ʽ.
			MyArrayList.this.remove(--currentIndex);
		}
		
		/*
		 * ע��:�ӵ�����ָ��� collection ���Ƴ�
		 * ���������ص����һ��Ԫ�أ���ѡ��������
		 * ÿ�ε��� next ֻ�ܵ���һ�δ˷�����
		 * ������е���ʱ�õ��ô˷���֮���������ʽ
		 * �޸��˸õ�������ָ��� collection��
		 * �����������Ϊ�ǲ�ȷ���ġ� 
		 */
		
	}
	
	
}
