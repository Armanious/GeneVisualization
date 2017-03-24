package org.armanious;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class BioGRID {

	private static final String BIOGRID_DOWNLOAD_URL = "http://thebiogrid.org/downloads/archives/Latest%20Release/BIOGRID-ALL-LATEST.tab2.zip";
	private static final File BIOGRID_DB_FOLDER = new File(System.getProperty("user.dir"), "BioGRID");
	private static File BIOGRID_DB_FILE;

	private static boolean alreadyChecked = false;

	private static void ensureBioGRIDdownloaded(boolean shouldOutput){
		BIOGRID_DB_FILE = new File(BIOGRID_DB_FOLDER, "BioGRID.txt");
		/*
		if(alreadyChecked) return;
		try{
			BIOGRID_DB_FOLDER.mkdirs();
			URL url = new URL(BIOGRID_DOWNLOAD_URL);
			ZipInputStream zis = new ZipInputStream(url.openStream());
			ZipEntry e = zis.getNextEntry();
			BufferedInputStream in = new BufferedInputStream(zis);
			BIOGRID_DB_FILE = new File(BIOGRID_DB_FOLDER, e.getName());
			if(!BIOGRID_DB_FILE.exists()){
				if(shouldOutput){
					System.out.println("Downloading new version of BioGRID: " + e.getName());
				}
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(BIOGRID_DB_FILE));
				final byte[] data = new byte[4096];
				int read;
				while((read = in.read(data, 0, 4096)) != -1){
					out.write(data, 0, read);
				}
				out.flush();
				out.close();
			}else{
				if(shouldOutput){
					System.out.println("Already have most recent version of BioGRID: " + e.getName());
				}
			}
			in.close();
			alreadyChecked = true;
		}catch(IOException e){
			System.err.println("Could not verify if BioGRID already downloaded and up-to-date.");
			e.printStackTrace();
		}*/
	}

	public static String[] getGenesForDbpecQuery(String fullQueryUrl){
		ensureBioGRIDdownloaded(false);
		final ArrayList<String> genes = new ArrayList<>();
		try {
			final BufferedReader br = new BufferedReader(new InputStreamReader(new URL(fullQueryUrl).openStream()));
			String s;
			while((s = br.readLine()) != null){
				if(s.contains("Gene Symbol")){

					int idx = s.indexOf("<td>", s.indexOf("Hgnc ID"));
					do {
						int endIdx = s.indexOf("</td>", idx + 1);
						String geneId = s.substring(idx + 4, endIdx);
						try{
							Integer.parseInt(geneId);
						}catch(Exception good){
							genes.add(geneId);
						}
					}while((idx = s.indexOf("<td>", idx + 1)) != -1);
					break;
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		String[] geneArr = genes.toArray(new String[genes.size()]);
		return geneArr;
	}

	private static final int HOMO_SAPIEN_NCBI_ID = 9606;

	public static Gene[] extractRelatedGenesFromBioGRID(String[] genes){
		Arrays.sort(genes);
		ensureBioGRIDdownloaded(true);
		try{
			final Map<String, Gene> geneList = new TreeMap<>();
			final BufferedReader br = new BufferedReader(new FileReader(BIOGRID_DB_FILE));
			br.readLine();//first line is headings
			String s;
			while((s = br.readLine()) != null){
				final String[] parts = s.split("\t");
				if(Integer.parseInt(parts[15]) != HOMO_SAPIEN_NCBI_ID ||
						Integer.parseInt(parts[16]) != HOMO_SAPIEN_NCBI_ID){
					continue;
				}
				if(parts[7].equals(parts[8])) continue;
				if(Arrays.binarySearch(genes, parts[7]) >= 0 &&
						Arrays.binarySearch(genes, parts[8]) >= 0){

					Gene g1 = geneList.get(parts[7]);
					if(g1 == null){
						g1 = new Gene(parts[7]);
						geneList.put(parts[7], g1);
					}

					Gene g2 = geneList.get(parts[8]);
					if(g2 == null){
						g2 = new Gene(parts[8]);
						geneList.put(parts[8], g2);
					}

					g1.connectTo(g2);
				}
			}
			br.close();
			return geneList.values().toArray(new Gene[geneList.size()]);
		}catch(IOException e){
			System.err.println("Error cross-checking with BioGRID!");
			return new Gene[0];
		}
	}

	public static Gene[] singleGeneSearch(String hgncId) {
		String singleGene = Utilities.getGeneById(Integer.parseInt(hgncId));
		if(singleGene == null) return new Gene[0];
		ensureBioGRIDdownloaded(true);
		Set<String> relatedGenes = new HashSet<>();
		relatedGenes.add(singleGene);
		try{
			final BufferedReader br = new BufferedReader(new FileReader(BIOGRID_DB_FILE));
			br.readLine();//first line is headings
			String s;
			while((s = br.readLine()) != null){
				final String[] parts = s.split("\t");
				if(Integer.parseInt(parts[15]) != HOMO_SAPIEN_NCBI_ID ||
						Integer.parseInt(parts[16]) != HOMO_SAPIEN_NCBI_ID){
					continue;
				}
				if(parts[7].equals(parts[8])) continue;
				if(parts[7].equals(singleGene)){
					if(Arrays.binarySearch(Utilities.SORTED_DBPEC_GENES, parts[8]) >= 0){
						relatedGenes.add(parts[8]);
					}
				}else if(parts[8].equals(singleGene)){
					if(Arrays.binarySearch(Utilities.SORTED_DBPEC_GENES, parts[7]) >= 0){
						relatedGenes.add(parts[7]);
					}
				}
			}
			br.close();
		}catch(IOException e){
			System.err.println("Error cross-checking with BioGRID!");
			return new Gene[0];
		}
		String[] genes = relatedGenes.toArray(new String[relatedGenes.size()]);
		Arrays.sort(genes);
		try{
			final Map<String, Gene> geneList = new TreeMap<>();
			final BufferedReader br = new BufferedReader(new FileReader(BIOGRID_DB_FILE));
			br.readLine();//first line is headings
			String s = null;
			while((s = br.readLine()) != null){
				final String[] parts = s.split("\t");
				if(Integer.parseInt(parts[15]) != HOMO_SAPIEN_NCBI_ID ||
						Integer.parseInt(parts[16]) != HOMO_SAPIEN_NCBI_ID){
					continue;
				}
				if(parts[7].equals(parts[8])) continue;
				if(Arrays.binarySearch(genes, parts[7]) >= 0 &&
						Arrays.binarySearch(genes, parts[8]) >= 0){

					Gene g1 = geneList.get(parts[7]);
					if(g1 == null){
						g1 = new Gene(parts[7]);
						geneList.put(parts[7], g1);
					}

					Gene g2 = geneList.get(parts[8]);
					if(g2 == null){
						g2 = new Gene(parts[8]);
						geneList.put(parts[8], g2);
					}

					g1.connectTo(g2);
				}
			}
			br.close();
			return geneList.values().toArray(new Gene[geneList.size()]);
		}catch(IOException e){
			System.err.println("Error cross-checking with BioGRID!");
			return new Gene[0];
		}
	}

}
