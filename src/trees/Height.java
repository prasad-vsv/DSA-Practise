package trees;

public class Height {
	int h;
	
	public Height(int h){
		this.h = h;
	}
	
	@Override
	public String toString(){
		return ""+h;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Height)){
			return false;
		}
		Height _height = (Height )o;
		return h == _height.h;
	}
}
