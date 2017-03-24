package org.armanious;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.armanious.layout.ArmaLayout;
import org.armanious.layout.ArmaLayoutDisconnectedGraph;
import org.armanious.layout.Graph;
import org.armanious.layout.Layout;

public class Utilities {
	

	public static final String[] SORTED_DBPEC_GENES = {"A1BG","A2M","ABCA1","ABCA4","ABCB1","ABCG1","ABCG2","ABO","ACADM","ACADS","ACE","ACE2","ACTA2","ACTB","ACTG1","ACVR1B","ACVR1C","ACVR2A","ACVR2B","ADAM12","ADIPOQ","ADIPOR1","ADIPOR2","ADM","ADORA1","ADORA2A","ADORA2B","ADORA3","ADRB3","AFP","AGER","AGT","AGTR1","AGTR2","AGTRL1","AHSA1","AHSG","AKR1B1","AKT1","ALB","AMBP","ANG","ANGPT1","ANGPT2","ANXA5","APC","APCS","APLN","APOA1","APOC3","APOE","AQP9","AR","ARG2","ASTN2","ATP2A1","ATP2A2","ATP2A3","ATP2B1","ATP2B4","ATP5A1","B2M","BAX","BCL2","BDKRB2","BGN","BOK","BSG","C19orf33","C3","C4A","C4orf45","C5AR1","C9orf11","CALB1","CALCA","CALCRL","CALR","CASP12","CASP3","CASP8","CAT","CCL2","CCND1","CCR2","CD14","CD163","CD19","CD209","CD24","CD247","CD36","CD40","CD40LG","CD44","CD46","CD55","CD58","CD59","CD63","CD68","CD74","CD8A","CDH1","CDH5","CDKN1A","CDKN1C","CEACAM8","CFH","CFI","CFTR","CGA","CGB","CHRNA7","CLDN1","CLDN3","CLDN5","CLU","CNR1","COL18A1","COL1A1","COMT","CPB2","CR1","CRH","CRHBP","CRHR1","CRHR2","CRP","CSAD","CSF2","CSF3","CSH1","CST3","CTBS","CTGF","CTLA4","CTNNB1","CTNND1","CTRL","CTSD","CTSL1","CTSL2","CUL4A","CUL7","CXCL1","CXCL10","CYBA","CYBB","CYP11B2","CYP17A1","CYP19A1","CYP1A1","CYP27B1","CYP8B1","CYR61","DARC","DCN","DDAH1","DDIT3","DIABLO","DLX4","DNMT1","DPP4","DRD2","DRD4","DYSF","EBAG9","EDN1","EDNRA","EDNRB","EGF","EGFR","EGLN1","EGLN2","EGLN3","EGR1","EIF2S1","ELN","ENG","EPAS1","EPHX1","EPO","ERAF","ERAP2","ERVWE1","ESR1","ESR2","ETFDH","EXOSC2","F10","F11","F2","F2R","F3","F5","F7","F8","FABP4","FAM198B","FAS","FASLG","FCGR3A","FGA","FGB","FGF2","FGG","FLT1","FN1","FNIP2","FOSB","FOXP3","FSTL3","FSTL5","FUBP3","FUT3","GADD45A","GAPDH","GC","GCM1","GDF15","GLRX","GNB3","GOT1","GOT2","GP1BA","GP9","GPT","GPT2","GPX1","GPX2","GPX3","GPX4","GSR","GSTCD","GSTM1","GSTP1","GSTT1","GTDC1","GTF2A1","H19","HADH","HADHA","HBA1","HBB","HBEGF","HGF","HIF1A","HIF1AN","HLA-A","HLA-B","HLA-C","HLA-DQA1","HLA-DQB1","HLA-DRA","HLA-DRB1","HLA-DRB3","HLA-DRB4","HLA-DRB5","HLA-G","HMOX1","HMOX2","HOXA9","HP","HPGD","HPX","HSD11B2","HSF1","HSP90AB1","HSP90B1","HSPA1A","HSPA1B","HSPA1L","HSPA4","HSPA5","HSPB1","HTRA1","HTRA3","ICAM1","ICAM3","ICOS","IFNG","IFNGR1","IFNGR2","IGF1","IGF2","IGF2R","IGFALS","IGFBP1","IGFBP2","IGFBP3","IGFBP5","IGFBP7","IL10","IL11","IL12A","IL15","IL16","IL18","IL1A","IL1B","IL1RAPL2","IL1RN","IL2","IL2RA","IL4","IL4R","IL5","IL6","IL6R","IL6ST","IL8","IL8RA","ILK","INDO","INHA","INHBA","INHBE","INSL3","ITGA1","ITGA4","ITGA5","ITGAL","ITGAM","ITGAV","ITGAX","ITGB1","ITGB2","ITGB3","ITPR1","ITPR2","JUN","JUNB","KCNE1L","KCNQ3","KCNQ5","KDR","KIF17","KIFC1","KIR2DL1","KIR2DL2","KIR2DL3","KIR2DL4","KIR2DL5A","KIR2DL5B","KIR2DS1","KIR2DS2","KIR2DS3","KIR3DL2","KIR3DS1","KISS1","KLRC1","KLRK1","KRT18","KRT8","LDHA","LDHB","LDHC","LDHD","LDLR","LEP","LEPR","LGALS1","LGALS13","LIFR","LIPC","LPA","LPL","LTF","MAOA","MAP1LC3A","MAP2K3","MAPK1","MAPK14","MAPK3","MAPK8","MAS1","MATN2","MBL2","MCAM","MCL1","MET","MIF","MIR128-1","MIR133B","MIR182","MIR21","MIR210","MIR302C","MIRN130B","MIRN155","MIRN210","MIRNLET7B","MKI67","MMP1","MMP2","MMP3","MMP7","MMP8","MMP9","MPO","MSTN","MT-CO1","MTHFR","MYH7","NAB1","NAMPT","NAT2","NCAM1","NCRNA00032","NDRG1","NES","NFE2L2","NFKB1","NFKB2","NFKBIA","NLRP3","NOD2","NODAL","NOS1","NOS2A","NOS3","NOSTRIN","NOV","NOX1","NPPA","NPPB","NPY","NR1H2","NR3C1","NR4A1","NTN1","OCLN","OGG1","OLR1","OSM","OXTR","P2RX4","P4HB","PAEP","PAFAH1B1","PAPPA","PAPPA2","PARD3","PDGFA","PDGFB","PDPN","PECAM1","PGAM1","PGF","PHC1","PIBF1","PIGF","PIK3R1","PITRM1","PKLR","PLAC1","PLAT","PML","PON1","POSTN","PPARA","PPARG","PPBP","PPID","PRCP","PRDM12","PRDX3","PRDX6","PRF1","PRKCA","PRKCD","PRNP","PROC","PROCR","PROK1","PROZ","PSMA1","PTGIS","PTGS1","PTGS2","PTH","PTHLH","PTX3","RAC1","RAGE","RAMP1","RAPGEF2","RASSF1","RELA","REN","RETN","RHD","RHOA","ROCK2","RORC","RXFP1","RXRA","RYR1","RYR2","RYR3","S100A6","S100B","S100G","SELE","SELL","SELP","SERPINA1","SERPINA3","SERPINB2","SERPINC1","SERPINE1","SF1","SH3PXD2A","SIAE","SIAH1","SIAH2","SIGLEC6","SLC22A3","SLC22A5","SLC25A20","SLC29A1","SLC29A2","SLC2A1","SLC4A1","SLC6A2","SLC7A1","SLC9A1","SLC9A3","SLCO4C1","SMAD2","SMAD7","SNAI1","SOCS3","SOD1","SOD2","SOD3","SP1","SPINK1","SPP1","SRY","STOX1","STOX2","TAC3","TACR3","TAF1","TAP1","TAP2","TAT","TBX21","TBXAS1","TEK","TERT","TFAP2A","TFAP2C","TFPI","TFPI2","TGFA","TGFB1","TGFB3","TGFBR1","TGFBR3","TH","THBD","TIE1","TIMP1","TIMP2","TIMP4","TLR2","TLR4","TMEM144","TNF","TNFRSF10B","TNFRSF1A","TNFRSF1B","TNFSF13B","TP53","TPI1","TRB@","TRPV5","TRPV6","TSPY1","TXN","UCN","UCN2","UCN3","VASH1","VCAM1","VCAN","VDR","VEGFA","VEGFC","VHL","VLDLR","VTN","VWF","XBP1","XDH","YWHAB","YWHAZ","ZCCHC7","ZFYVE9"};
	private static final int[] SORTED_DBPEC_GENE_HGNC_IDS = {5,7,29,34,40,73,74,79,89,90,2707,13557,130,132,144,172,18123,173,174,190,13633,24040,24041,259,262,263,264,268,288,317,320,333,336,338,339,1189,349,381,391,399,453,483,484,485,543,583,584,16665,600,610,613,643,644,664,17021,811,812,813,814,817,823,914,959,990,1030,1044,1087,1116,16668,1318,1323,26342,1338,1359,1434,1437,16709,1455,19004,1504,1509,1516,10618,1582,1603,1628,1631,1633,1641,1645,1677,1663,11919,11935,1681,6953,2665,1688,1689,1692,1693,1697,1706,1748,1764,1784,1786,1820,4883,5394,1884,1885,1886,1960,2032,2045,2047,2095,2159,2195,2197,2228,2300,2334,2355,2356,2357,2358,2367,18966,2434,2438,2440,2475,2496,2500,2505,2514,2515,2524,2529,2537,2538,2554,21024,4602,10637,2577,2578,2592,2593,2594,2595,2606,2653,2654,4035,2705,2715,2726,21528,2917,2976,3009,3023,3025,3097,3123,3176,3179,3180,3229,3236,1232,14660,14661,3238,3265,3327,3349,3374,3401,3415,18075,29499,13525,3467,3468,3483,17097,3528,3529,3535,3537,3541,3542,3544,3546,3559,25312,11920,11936,3619,3661,3662,3676,3694,3763,3778,29280,3797,6106,3973,21386,4005,4014,4095,4141,4187,4197,30142,4330,4400,4432,4433,4439,4444,4552,18062,4553,4554,4555,4556,4623,25806,4632,4638,4641,20887,4646,4713,4799,4801,4823,4827,3059,4893,4910,17113,4931,4932,4933,4942,4944,4947,4948,4951,4952,4953,4964,5013,5014,5109,5141,5154,5171,5209,5224,5258,12028,5232,5233,5234,5237,5238,5246,9476,30406,5344,5346,5351,5438,5439,5440,5464,5466,5467,5468,5469,5471,5472,5474,5476,5962,5966,5969,5977,5980,5986,5991,5992,5997,6000,6001,6008,6014,6015,6016,6018,6019,6021,6025,6026,6040,6059,6065,6066,24029,6086,6134,6140,6141,6148,6149,6150,6152,6153,6155,6156,6180,6181,6204,6205,6241,6297,6299,6307,19167,6389,6329,6330,6331,6332,16345,16346,6333,6334,6335,6339,6340,6341,6374,18788,6430,6446,6535,6541,6544,19708,6547,6553,6554,6561,15449,6597,6619,6667,6677,6720,6833,6838,6843,6871,6876,6877,6881,6899,6908,6922,6934,6943,7029,7097,31510,31759,31553,31586,31587,31764,31515,31542,31587,31479,7107,7155,7166,7173,7174,7175,7176,7218,4223,7419,7436,7577,7626,30092,7646,7656,16506,7679,7756,7782,7794,7795,7797,16400,5331,7865,7872,7873,7876,20203,7885,7889,7939,7940,7955,7965,7978,7980,8029,8104,8125,8133,8506,8529,8535,8548,8573,8574,8602,14615,16051,8799,8800,29602,8823,8888,8893,3182,23352,8962,8979,17663,9020,9044,9051,9113,9204,16953,9232,9236,9240,9257,9344,13997,9354,16753,9360,9393,9399,9449,9451,9452,18454,9460,9530,9603,9604,9605,9606,9607,9692,9801,9833,9843,16854,9882,9955,9958,20389,10009,667,10252,10260,19718,10477,10483,10484,10485,10496,10500,1436,10718,10720,10721,8941,16,8584,775,8583,12950,23664,18187,10857,10858,10875,10967,10969,1421,11003,11004,11005,11027,11048,11057,11071,11073,23612,6768,6773,11128,19391,11179,11180,11181,11205,11244,11255,11311,23508,25450,11521,11528,11535,43,44,11573,11599,11609,11724,11730,11742,11744,11760,11761,11765,11766,11769,11772,11774,11782,11784,11809,11820,11821,11823,11848,11850,25633,11892,11905,11916,11917,11929,11998,12009,12155,3145,14006,12381,12435,12516,18414,17781,19964,12663,2464,12679,12680,12682,12687,12698,12724,12726,12801,12805,12849,12855,26209,6775};
	
	public static int getHgncId(String geneName){
		final int idx = Arrays.binarySearch(SORTED_DBPEC_GENES, geneName);
		if(idx < 0) return -1;
		return SORTED_DBPEC_GENE_HGNC_IDS[idx];
	}
	
	public static String getGeneById(int hgncId) {
		for(int i = 0; i < SORTED_DBPEC_GENE_HGNC_IDS.length; i++){
			if(hgncId == SORTED_DBPEC_GENE_HGNC_IDS[i]){
				return SORTED_DBPEC_GENES[i];
			}
		}
		return null;
	}
	
	public static Gene[] jsonToGeneArray(String json){
		//TODO make more robust
		String[] parts = json.split(";");
		Gene[] arr = new Gene[Integer.parseInt(parts[0])];
		for(int i = 0; i < arr.length; i++){
			String[] geneInfo = parts[i + 1].split(",");
			arr[i] = new Gene(geneInfo[2]);
			arr[i].setPosition(PhysicsVector.fromXY(Double.parseDouble(geneInfo[0]), Double.parseDouble(geneInfo[1])));
		}
		String[] relations = parts[1 + arr.length].split(",");
		for(int i = 0; i < relations.length / 2; i++){
			arr[Integer.parseInt(relations[2 * i])].connectTo(arr[Integer.parseInt(relations[2 * i + 1])]);
		}
		return arr;
	}
	
	public static String geneToJson(Gene gene){
		final StringBuilder json = new StringBuilder("{\"x\":");
		final PhysicsVector pos = gene.getPosition();
		json.append(pos.getX()).append(",\"y\":").append(pos.getY())
			.append(",\"name\":\"").append(gene.getName()).
			append("\",\"hgncId\":").append(gene.getHgncID()).append('}');
		return json.toString();
	}
	
	public static String geneArrayToJson(Gene[] array){
		final StringBuilder json = new StringBuilder();
		json.append("{\"genes\":[");
		for(int i = 0; i < array.length; i++){
			json.append(geneToJson(array[i])).append(i == array.length - 1 ? "" : ",");
		}
		json.append(']');
		json.append(",\"relations\":[");
		for(int i = 0; i < array.length; i++){
			for(int j = i + 1; j < array.length; j++){
				if(array[i].isConnectedTo(array[j])){
					json.append(i).append(',').append(j).append(',');
				}
			}
		}
		if(json.charAt(json.length() - 1) == ','){
			json.replace(json.length() - 1, json.length(), "]}");
		}else{
			json.append("]}");
		}
		return json.toString();
	}
	
	@Deprecated
	public static String geneArrayToJsonLegacy(Gene[] array){
		final StringBuilder json = new StringBuilder();
		json.append("{\"names\":[");
		for(int i = 0; i < array.length - 1; i++){
			json.append('\"').append(array[i].getName()).append("\",");
		}
		json.append('\"').append(array[array.length - 1].getName()).append("\"]");
		
		json.append(", \"relations\":[");
		for(int i = 0; i < array.length; i++){
			for(int j = i + 1; j < array.length; j++){
				if(array[i].isConnectedTo(array[j])){
					json.append(i).append(',').append(j).append(',');
				}
			}
		}
		json.replace(json.length() - 1, json.length(), "");
		
		json.append("], \"locations\":[");
		for(int i = 0; i < array.length - 1; i++){
			PhysicsVector pv = array[i].getPosition();
			json.append(pv.getX()).append(',').append(pv.getY()).append(',');
		}
		json.append(array[array.length - 1].getPosition().getX()).append(',')
			.append(array[array.length - 1].getPosition().getY()).append(']');
		json.append("}");
		
		return json.toString();
	}
	
	public static byte[] fullyReadInputStream(InputStream in) throws IOException {
		if(!(in instanceof BufferedInputStream)){
			in = new BufferedInputStream(in);
		}
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final byte[] data = new byte[4096];
		int read;
		while((read = in.read(data, 0, 4096)) != -1){
			baos.write(data, 0, read);
		}
		in.close();
		return baos.toByteArray();
	}
	
	public static void layout(Gene[] genes){
		Graph g = new Graph(genes);
		for(Gene gene : genes){
			if(gene.getPosition() == PhysicsVector.ZERO_VECTOR){
				Layout.hueristicLayout(g);
				break;
			}
		}
		Layout layout;
		if(g.isConnectedGraph()){
			layout = new ArmaLayout(g);
		}else{
			layout = new ArmaLayoutDisconnectedGraph(g);
		}
		final Thread t = new Thread(layout::performLayout);
		t.start();
		for(int i = 0; i < 10; i++){
			if(!t.isAlive()) break;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
		if(t.isAlive()){
			t.interrupt();
		}
		g.translate(g.getPosition().multiply(-1));
	}
	
}
