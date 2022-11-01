package edu.indra.alumnos.dto;

/**
	{
	    "categories": [],
	    "created_at": "2020-01-05 13:42:26.194739",
	    "icon_url": "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
	    "id": "-PEc-qmSRCCAngDInERh9A",
	    "updated_at": "2020-01-05 13:42:26.194739",
	    "url": "https://api.chucknorris.io/jokes/-PEc-qmSRCCAngDInERh9A",
	    "value": "The only type of fever Chuck Norris ever gets is disco fever."
	}
*/

public class FraseChuckNorris {

	private String value = null;

	public FraseChuckNorris(String value) {
		super();
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "FraseChuckNorris [value=" + value + "]";
	}
	
}
