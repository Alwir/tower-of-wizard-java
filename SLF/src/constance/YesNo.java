package constance;

import gui.Messages;

public enum YesNo {
	CANCEL,YES,NO,OK;

	public static YesNo GetYesNo(int nom) {
		return YesNo.values()[nom];
	}
	
	public static String[] GetYesNoText(YesNo[] type){
		String [] answer=new String[type.length];
		for (int i=0;i<type.length;i++) {
			switch (type[i]) {
				case CANCEL: answer[i]=Messages.getString("buttonTextCancel"); break;
				case YES: answer[i]=Messages.getString("buttonTextYes"); break;
				case NO: answer[i]=Messages.getString("buttonTextNo"); break;
				case OK: answer[i]=Messages.getString("buttonTextOk"); break;
			}
		}
		return answer;
	}

}
