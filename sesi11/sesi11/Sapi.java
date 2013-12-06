class Sapi extend Hewan{
	public Sapi (){
		super("Sapi", 4, false);
	}
	public void bersuara(){
		System.out.println("\nemoh, emoh...");
	}
	public static void main (String[] args){
		Sapi S= new Sapi();
		s.isHewan();
		s.bersuara();
	}
}