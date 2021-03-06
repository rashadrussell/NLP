package edu.nyu.cs.pub;

/**
 * Token Model<br/>
 * A list of tokens form a sentence.
 * 
 * @author Daniel Wu
 * 
 */
public class Token implements Comparable<Token>, Cloneable {

	// index from original input
	private int index;

	// upper case of original word
	private String literal;

	// original word
	private String originWord;

	// part of speech tag
	private String posTag;

	// sentence index from original input
	private String sentIndex;

	// predict name group tag
	private String predictBIOChunk;

	// name group tag:read from file
	private String BIOChunk;

	// predict relation type
	private String predictRelType;

	// original relation type
	private String originalRelType;

	// sequence number
	private String seqNo;

	private Token(int seq, String literal, String tag, String originalTag,
			String seqNo, String sentIndex, String originalRelType) {
		this.index = seq;
		this.originWord = literal;
		this.literal = literal;
		this.posTag = (tag == null) ? "" : tag.trim().toUpperCase();
		this.BIOChunk = originalTag;
		// this.relType = relType;
		this.originalRelType = originalRelType;
		this.seqNo = seqNo;
		this.sentIndex = sentIndex;
	}

	public String getSentIndex() {
		return sentIndex;
	}

	public String getOriginalRelType() {
		return originalRelType;
	}

	public void setOriginalRelType(String originalRelType) {
		this.originalRelType = originalRelType;
	}

	public void setSentIndex(String sentIndex) {
		this.sentIndex = sentIndex;
	}

	public String getPredictBIOChunk() {
		return predictBIOChunk;
	}

	public String getBIOChunk() {
		return BIOChunk;
	}

	public void setCorrectType(String correctType) {
		this.BIOChunk = correctType;
	}

	public String getOriginWord() {
		return originWord;
	}

	public String getLiteral() {
		return literal;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}

	public String getPosTag() {
		return posTag;
	}

	public void setTag(String tag) {
		this.posTag = tag;
	}

	public void setEntityType(String entityType) {
		this.predictBIOChunk = entityType;
	}

	public void setPredictRelType(String relType) {
		this.predictRelType = relType;
	}

	public String getPredictRelType() {
		return this.predictRelType;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String strIndex) {
		this.seqNo = strIndex;
	}

	public boolean isPRED() {
		return "PRED".equals(this.originalRelType);
	}

	public boolean isARG() {
		return "ARG".equals(this.originalRelType);
	}

	public boolean isSUPPORT() {
		return "SUPPORT".equals(this.originalRelType);
	}

	@Override
	public String toString() {
		return "Token [index=" + index + ", literal=" + literal
				+ ", originWord=" + originWord + ", posTag=" + posTag
				+ ", sentIndex=" + sentIndex + ", ngTag=" + predictBIOChunk
				+ ", originalNgTag=" + BIOChunk + ", relType=" + predictRelType
				+ ", originalRelType=" + originalRelType + ", seqNo=" + seqNo
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((literal == null) ? 0 : literal.hashCode());
		result = prime * result
				+ ((predictBIOChunk == null) ? 0 : predictBIOChunk.hashCode());
		result = prime * result
				+ ((originWord == null) ? 0 : originWord.hashCode());
		result = prime * result
				+ ((BIOChunk == null) ? 0 : BIOChunk.hashCode());
		result = prime * result + ((posTag == null) ? 0 : posTag.hashCode());
		result = prime * result
				+ ((predictRelType == null) ? 0 : predictRelType.hashCode());
		result = prime * result
				+ ((sentIndex == null) ? 0 : sentIndex.hashCode());
		result = prime * result + ((seqNo == null) ? 0 : seqNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		if (literal == null) {
			if (other.literal != null)
				return false;
		} else if (!literal.equals(other.literal))
			return false;
		if (predictBIOChunk == null) {
			if (other.predictBIOChunk != null)
				return false;
		} else if (!predictBIOChunk.equals(other.predictBIOChunk))
			return false;
		if (originWord == null) {
			if (other.originWord != null)
				return false;
		} else if (!originWord.equals(other.originWord))
			return false;
		if (BIOChunk == null) {
			if (other.BIOChunk != null)
				return false;
		} else if (!BIOChunk.equals(other.BIOChunk))
			return false;
		if (posTag == null) {
			if (other.posTag != null)
				return false;
		} else if (!posTag.equals(other.posTag))
			return false;
		if (predictRelType == null) {
			if (other.predictRelType != null)
				return false;
		} else if (!predictRelType.equals(other.predictRelType))
			return false;
		if (sentIndex == null) {
			if (other.sentIndex != null)
				return false;
		} else if (!sentIndex.equals(other.sentIndex))
			return false;
		if (seqNo == null) {
			if (other.seqNo != null)
				return false;
		} else if (!seqNo.equals(other.seqNo))
			return false;
		return true;
	}

	@Override
	protected Token clone() {
		return new Token(index, literal, posTag, BIOChunk, seqNo, sentIndex,
				predictRelType);
	}

	public static class TokenDecor {

		public static final String TAB = "\t";

		public static final String BLANK = " ";

		private Token token;

		private String delimiter = TAB;

		public TokenDecor(Token token, String delimiter) {
			this.token = token;
			this.delimiter = delimiter;
		}

		public String toString() {
			StringBuffer strBuff = new StringBuffer();
			strBuff.append(token.getOriginWord());
			if (!TextTool.isEmpty(token.getPosTag())) {
				strBuff.append(delimiter + token.getPosTag());
			}
			if (!TextTool.isEmpty(token.getBIOChunk())) {
				strBuff.append(delimiter + token.getBIOChunk());
			}
			String predictBIO = token.getPredictBIOChunk();
			if (!TextTool.isEmpty(predictBIO)) {
				strBuff.append(delimiter + predictBIO);
			}
			if (!TextTool.isEmpty(token.getSeqNo())) {
				strBuff.append(delimiter + token.getSeqNo());
			}
			if (!TextTool.isEmpty(token.getSentIndex())) {
				strBuff.append(delimiter + token.getSentIndex());
			}
			String orginalType = token.getOriginalRelType();
			if (!TextTool.isEmpty(orginalType)) {
				strBuff.append(delimiter + orginalType);
			}

			String relType = token.getPredictRelType();
			if (!TextTool.isEmpty(relType)) {
				strBuff.append(delimiter + relType);
			}
			return strBuff.toString();
		}
	}

	/**
	 * Token builder<br/>
	 * 
	 * @author Daniel Wu
	 * 
	 */
	public static class TokenBuilder {

		private int index;

		private String literal = "";

		private String posTag = "";

		private String sentIndex = "";

		// name group tag:read from file
		private String originalNgTag = "";

		private String relType = "";

		private String seqNo = "";

		public TokenBuilder(String[] strs) {
			if (strs.length > 0) {
				this.setLiteral(strs[0]);
			}
			if (strs.length > 1) {
				this.setPosTag(strs[1]);
			}
			if (strs.length > 2) {
				this.setOriginalNgTag(strs[2]);
			}
			if (strs.length > 3) {
				this.setSeqNo(strs[3]);
			}
			if (strs.length > 4) {
				this.setSentIndex(strs[4]);
			}
			if (strs.length > 5) {
				this.setOriginalRelType(strs[5]);
			}
		}

		public Token build() {
			this.posTag = (posTag == null) ? "" : posTag.trim().toUpperCase();
			return new Token(index, literal, posTag, originalNgTag, seqNo,
					sentIndex, relType);
		}

		public void setLiteral(String literal) {
			this.literal = literal;
		}

		public void setPosTag(String posTag) {
			this.posTag = posTag;
		}

		public void setSentIndex(String sentIndex) {
			this.sentIndex = sentIndex;
		}

		public void setOriginalNgTag(String originalNgTag) {
			this.originalNgTag = originalNgTag;
		}

		public void setOriginalRelType(String relType) {
			this.relType = relType;
		}

		public void setSeqNo(String strIndex) {
			this.seqNo = strIndex;
		}

		public void setIndex(int index) {
			this.index = index;
		}

	}

	@Override
	public int compareTo(Token o) {
		return (this.index < o.index) ? -1 : 1;
	}

}
