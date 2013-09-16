package inria.FAMILIAR.Readers;

public class test {
	static String vml2FilePath = "/Users/ealferez/git/VM/fr.inria.lang.vml2.example.MOTIV/VideoContent.vm";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VMReader myReader = new VMReader();
		try {
		System.out.println(myReader.parseFile(vml2FilePath));
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
}