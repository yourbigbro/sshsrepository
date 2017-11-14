package domain;

public class User {
	private String pid;
	private String word;
	private String pinyin;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String pid, String word, String pinyin) {
		super();
		this.pid = pid;
		this.word = word;
		this.pinyin = pinyin;
	}
	
}
