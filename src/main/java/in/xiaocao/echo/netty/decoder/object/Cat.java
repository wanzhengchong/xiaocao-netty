package in.xiaocao.echo.netty.decoder.object;

public class Cat implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5439331628293386489L;

	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Cat [name=" + name + ", age=" + age + "]";
	}

}
