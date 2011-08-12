package org.rcs.convert;

public enum ConversionType {
	
	ENCODE(".txt", ".rcs"), DECODE(".rcs", ".txt");

	private String sourceFileExtension;
	private String destFileExtension;

	private ConversionType(String s1, String s2) {
		this.sourceFileExtension = s1;
		this.destFileExtension = s2;
	}

	public String getSourceFileExtension() {
		return sourceFileExtension;
	}

	public String getDestFileExtension() {
		return destFileExtension;
	}

}
