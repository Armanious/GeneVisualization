package org.armanious;

public class CommandLineEntry {
	
	public static void main(String...args) throws Exception {
		System.setErr(System.out);
		//args = new String[]{"http://ptbdb.cs.brown.edu/dbpec/browse_search2.php?PE=1&EO=1&M=1"};
		//if(args.length != 1){
			//System.err.println("The only parameter should be the full URL of the dbPEC query that will be visualized OR a JSON-encoded array of the gene information.");
			//System.exit(1);
		//}
		if(args.length != 1 || (args = args[0].split("\n")).length != 2){
			System.out.println("Incorrect usage: Should be type\\ndata");
			System.exit(0);
		}
		final String update = args[0];
		final String data = args[1];
		Gene[] genes;
		if(update.equals("relayout")){
			genes = Utilities.jsonToGeneArray(data);
		}else if(update.equals("initial")){
			genes = BioGRID.extractRelatedGenesFromBioGRID(data.split(","));
		}else if(update.equals("singlegene")){
			genes = BioGRID.singleGeneSearch(data);
		}else{
			genes = new Gene[0];
		}
		if(genes.length > 1){
			Utilities.layout(genes);
		}
		
		System.out.println(Utilities.geneArrayToJson(genes));
	}

}
