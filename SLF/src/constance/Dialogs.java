package constance;

public enum Dialogs {
	MESSAGE,
	DIALOG,
	BIGDIALOG;
	
	public static YesNo[] GetAnswer(Dialogs type)
	{
		switch(type) {
			case MESSAGE: return new YesNo[]{YesNo.OK};
			case DIALOG: return new YesNo[]{YesNo.YES,YesNo.NO};
			case BIGDIALOG: return new YesNo[]{YesNo.YES,YesNo.NO,YesNo.CANCEL};	
		}
		return null;
	}
}