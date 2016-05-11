package gui;
class BackgrThread extends Thread
{
	private BackgrPanel g;
	
	public BackgrThread(BackgrPanel canvas) {
		g=canvas;
	}

	public void run() {
		while(true) {
		    g.incrsm();
		    g.repaint();
	        try {
	        	sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}   
}