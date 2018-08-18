public class Card {
	private String _word;
	private String _def;
	private Boolean is_word = true;
	private Boolean is_def = false;
	
	Card(String word, String def) {
		_word = word;
		_def = def;
	}
	
	public String getWord() { return _word; }
	public String getDef() { return _def; }
	public void setWord(String word) { _word = word; }
	public void setDef(String def) { _def = def; }
	
	public String flip_card() {
		if (is_word == true) {
			is_word = false;
			is_def = true;
			return _def;
		} else {
			is_word = false;
			is_def = true;
			return _word;
		}
	}
}