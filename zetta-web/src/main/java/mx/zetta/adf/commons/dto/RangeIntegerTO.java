package mx.zetta.adf.commons.dto;

import java.io.Serializable;

public class RangeIntegerTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer init;
	private Integer end;

	public RangeIntegerTO() {
	}

	public RangeIntegerTO(Integer init, Integer end) {
		super();
		this.init = init;
		this.end = end;
	}

	public Integer getInit() {
		return init;
	}

	public void setInit(Integer init) {
		this.init = init;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

}
