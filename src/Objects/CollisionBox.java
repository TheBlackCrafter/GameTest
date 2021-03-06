package Objects;

public class CollisionBox {

	private float left,right,bottom,top,x,y;
	private String lastSide = "";   //left, right, top, bottom

	public boolean tuches(CollisionBox b){
		if(b.getLeft() <= this.right && b.right >= this.getLeft()){
			if(b.getBottom() <= this.top && b.getTop() >= this.getBottom()){
				
				return true;
			}
		}
		return false;
	}
	
	//   \/  getters and setters  \/
	
	/**
	 * @return the bottom
	 */
	public float getBottom() {
		return bottom;
	}

	/**
	 * @param bottom the bottom to set
	 */
	public void setBottom(float bottom) {
		this.bottom = bottom;
	}

	/**
	 * @return the left
	 */
	public float getLeft() {
		return left;
	}

	/**
	 * @param left the left to set
	 */
	public void setLeft(float left) {
		this.left = left;
	}

	/**
	 * @return the top
	 */
	public float getTop() {
		return top;
	}

	/**
	 * @param top the top to set
	 */
	public void setTop(float top) {
		this.top = top;
	}

	/**
	 * @return the right
	 */
	public float getRight() {
		return right;
	}

	/**
	 * @param right the right to set
	 */
	public void setRight(float right) {
		this.right = right;
	}
	
	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @return the lastSide
	 */
	public String getLastSide() {
		return lastSide;
	}
}
